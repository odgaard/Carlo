

type Group
    liberties::Int64
    members::Set
end

function selfAtari(board::Array, coordinate::Int64, player::Int64)
    currBoard = board
    futBoard = copy(board)
    performMove(futBoard, coordinate, player)
    #Handle returning the enemy groups to previous state, by adding liberties again
    if futBoard[coordinate] == 0
        return true
    end
    return false
end

function ko(boards::Array, coordinate::Int64, player::Int64)
    #Has already passed other tests, so it's a valid move, except for ko?
    prevBoard = boards[1]
    futBoard = copy(boards[2])
    performMove(futBoard, coordinate, player)
    for i=1:length(prevBoard)
        if prevBoard[i] != futBoard[i]
            return false
        end
    end
    return true
end

function removeGroup(coordinate)
    print("Removed group!", coordinate, groupDict[coordinate])
end

function validMove(boards::Array, coordinate::Int64, player::Int64)
    board = boards[2]
    if (board[coordinate] > 0)
        println("You can't place on top of other stones!")
        return false
    end

    if selfAtari(board, coordinate, player)
        println("You can't put yourself in atari")
        return false
    end

    if ko(boards, coordinate, player)
        println("This is a ko. Play somewhere else.")
        return false
    end

    return true
end

function performMove(board::Array, coordinate::Int64, player::Int64)
#If player 1 played the move, check if player 2 was surrounded first, then
#check if player 1 was surrounded afterwards
#TODO

    board[coordinate] = player
    player == 1 ? otherPlayer = 2 : otherPlayer = 1

    thisStone = Group(4, Set(coordinate))
    #FIX PROBLEM WITH HAVING LIBERTIES BY LOOPING AROUND THE BOARD
    for check in [-size, -1, 1, size]
        if coordinate+check < 1
            thisStone.liberties -= 1
        if coordinate+check > size*size
            thisStone.liberties -= 1
        if board[coordinate+check] == otherPlayer
            thisStone.liberties -= 1


    groupDict[coordinate] = thisStone
    enemyStones = Array{Int64}(1)
    for check in [-size, -1, 1, size]
        if coordinate+check >= 1 && coordinate+check <=size*size
            if board[coordinate+check] == otherPlayer
                push!(enemyStones, coordinate+check)
                groupDict[coordinate+check].liberties -= 1
                if groupDict[coordinate+check].liberties <= 0
                    removeGroup(coordinate+check)
                else
                    groupDict[coordinate].liberties -= 1
                    if groupDict[coordinate].liberties <= 0
                        println("SELF ATARI!!!")
                        removeGroup(coordinate)
                        for enemy in enemyStones
                            groupDict[enemy].liberties += 1
                        end
                    end
                end
            elseif board[coordinate+check] == player
                print("Friendly stone", player)
                if coordinate in groupDict[coordinate+check].members
                else
                    union(groupDict[coordinate+check].members, groupDict[coordinate].members)
                    groupDict[coordinate+check].liberties += groupDict[coordinate].liberties - 1
                    groupDict[coordinate] = groupDict[coordinate+check]
                    println(groupDict[coordinate], groupDict[coordinate+check])

                end
            end
        else
            groupDict[coordinate].liberties -= 1

        end
    end
end

function makeMove(boards::Array, coordinate::Int64, player::Int64)
    if validMove(boards, coordinate, player)
        currBoard = copy(boards[2])
        performMove(currBoard, coordinate, player)
        boards[1] = copy(boards[2])
        boards[2] = currBoard
    else
        println("That is an invalid move")
    end
end


global scores = zeros(Int64, 2)
global size = 3
global groupDict = Dict()

board1 = zeros(Int64, size, size)
board2 = zeros(Int64, size, size)

boards = Array(Array, 2)
boards[1] = board1
boards[2] = board2
println(boards)

makeMove(boards, 1, 1)
println(boards)

makeMove(boards, 4, 1)
println(boards)

makeMove(boards, 2, 2)
println(boards)

makeMove(boards, 5, 2)
println(boards)

makeMove(boards, 7, 2)
println(boards)
