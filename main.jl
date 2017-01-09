
function selfAtari(board::Array, coordinate::Int64, player::Int64)
    currBoard = board
    futBoard = copy(board)
    performMove(futBoard, coordinate, player)
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

function validMove(boards::Array, coordinate::Int64, player::Int64)
    board = boards[2]
    if (board[coordinate] > 0)
        println("You can't place on top of other stones!")
        return false
    end

#    if selfAtari(board, coordinate, player)
#        println("You can't put yourself in atari")
#        return false
#    end

#    if ko(boards, coordinate, player)
#        println("This is a ko. Play somewhere else.")
#        return false
#    end

    return true
end

function checkSurrounded(board::Array, coordinate::Int64, checked::Array)
#TODO
    if coordinate - size >= 1 && !(coordinate-size in checked)
        println(coordinate-size, checked)
        println("test1", )
        println(board[coordinate-size])
        if board[coordinate-size] == 0
            print("false")
            return false
        end
        if board[coordinate-size] == board[coordinate]
            push!(checked, coordinate-size)
            println("Go?1")
            return checkSurrounded(board, coordinate-size, checked)
        end
    end

    if coordinate-1 >= 1 && !(coordinate-1 in checked)
        println("test2")
        println(board[coordinate-1])
        if board[coordinate-1] == 0
            print("false")
            return false
        end
        if board[coordinate-1] == board[coordinate]
            println("Go?2")
            push!(checked, coordinate-1)
            return checkSurrounded(board, coordinate-1, checked)
        end
    end

    if coordinate + 1 <= size*size && !(coordinate+1 in checked)
        println("test3")
        println(board[coordinate+1])
        if board[coordinate+1] == 0
            print("false")
            return false
        end
        if board[coordinate+1] == board[coordinate]
            println("Go?3")
            push!(checked, coordinate+1)
            return checkSurrounded(board, coordinate+1, checked)
        end
    end

    if coordinate + size <= size*size && !(coordinate+size in checked)
        println("test4")
        println(board[coordinate+size])
        if board[coordinate+size] == 0
            print("false")
            return false
        end
        if board[coordinate+size] == board[coordinate]
            push!(checked, coordinate+size)
            println("Go?4")
            return checkSurrounded(board, coordinate+size, checked)
        end
    end
    println("True", coordinate, checked)
    push!
    return true
end


function performMove(board::Array, coordinate::Int64, player::Int64)
#If player 1 played the move, check if player 2 was surrounded first, then
#check if player 1 was surrounded afterwards
#TODO

    board[coordinate] = player
    player == 1 ? otherPlayer = 2 : otherPlayer = 1

    checked = zeros(Int64, 0)
    #checked[1] = coordinate
    if coordinate-size >= 1
        if board[coordinate-size] == otherPlayer
            push!(checked, coordinate-size)
            if checkSurrounded(board, coordinate-size, checked)
                println("Captured stones!1", checked)
                #Remove checked stones
            end
        end
    end

    if coordinate-1 >= 1
        if board[coordinate-1] == otherPlayer
            push!(checked, coordinate-1)
            if checkSurrounded(board, coordinate-1, checked)
                println("Captured stones!2", checked)
            end
        end
    end

    if coordinate+1 <= size*size
        if board[coordinate+1] == otherPlayer
            push!(checked, coordinate+1)
            if checkSurrounded(board, coordinate+1, checked)
                println("Captured stones!3", checked)
            end
        end
    end

    if coordinate+size <= size*size
        if board[coordinate+size] == otherPlayer
            push!(checked, coordinate+size)
            if checkSurrounded(board, coordinate+size, checked)
                println("Captured stones!4", checked)
            end
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

#TODO
#Lag en min_priority_queue med BFS og bruk den for checkSurrounded


global scores = zeros(Int64, 2)
global size = 3

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
