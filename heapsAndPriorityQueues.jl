module minheap
function min_heapify(A, i)
    left = i << 1
    right = left + 1
    if left <= length(A) && A[left] < A[i]
        smallest = left
    else
        smallest = i
    end
    if right <= length(A) && A[right] < A[smallest]
        smallest = right
    end
    if smallest != i
        A[smallest], A[i] = A[i], A[smallest]
        min_heapify(A, smallest)
    end
end

function build_min_heap(A)
    for i = div(length(A),2):-1:1
        min_heapify(A,i)
    end
end

function heappop(A)
    result = shift!(A)
    min_heapify(A, 1)
    return result
end

function main()
    size = 30
    normalList = Array(Int64, size)
    normalList2 = Array(Int64, size)
    for i=1:size
        random = rand(0:size*3)
        normalList[i] = random
        normalList2[i] = random
    end
    @time build_min_heap(normalList)
end
end


module min_priority_queue
count = 0

function build_min_priority_queue(A)
    minheap.build_min_heap(A)
end

function extract_min(A)
    return minheap.heappop(A)
end




minheap.main()
