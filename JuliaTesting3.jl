module maxheap
function max_heapify(A, i)
    l = 2i
    r = 2i+1
    if l <= length(A) && A[l] > A[i]
        largest = l
    else
        largest = i
    end
    if r <= length(A) && A[r] > A[largest]
        largest = r
    end
    if largest != i
        A[largest], A[i] = A[i], A[largest]
        max_heapify(A, largest)
    end
end

function build_max_heap(A)
    for i = div(length(A),2):-1:1
        max_heapify(A,i)
    end
end

function main()
    size = 10
    normalList = Array(Int64, size)
    for i=1:size
        normalList[i] = rand(0:20)
    end
    normalList = [16,4,7,1,14,2,8,10,9,3]
    println(normalList)
    build_max_heap(normalList)
    println(normalList)
end
end
maxheap.main()
