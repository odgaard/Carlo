function generateUnsortedList(size)
  unsortedList = Array(Int64, size)
  for i = 1:size
    unsortedList[i] = rand(1:100000000)
  end
  return unsortedList
end

function mergesort(A)
    if length(A) <= 1
        return A
    end
    middle = div(length(A), 2)
    left = mergesort(A[1:middle])
    right = mergesort(A[middle + 1:end])
    result = Array(eltype(left), length(left) + length(right))
    idx = 1
    l = 1
    r = 1
    @inbounds while l <= length(left) && r <= length(right)
        if left[l] <= right[r]
            result[idx] = left[l]
            l += 1
        else
            result[idx] = right[r]
            r += 1
        end
        idx += 1
    end
    @inbounds while l <= length(left)
        result[idx] = left[l]
        l += 1
        idx += 1
    end
    @inbounds while r <= length(right)
        result[idx] = right[r]
        r += 1
        idx += 1
    end
    return result
end

function quicksort(A,i=1,j=length(A))
    if j > i
        pivot = A[rand(i:j)] # random element of A
        left, right = i, j
        @inbounds while left <= right
            @inbounds while A[left] < pivot
                left += 1
            end
            @inbounds while A[right] > pivot
                right -= 1
            end
            if left <= right
                A[left], A[right] = A[right], A[left]
                left += 1
                right -= 1
            end
        end
        quicksort(A,i,right)
        quicksort(A,left,j)
    end
    return A
end

function main()
  @time unsortedList = generateUnsortedList(100000000)
  #@time mergesort(unsortedList)
  @time quicksort(unsortedList)
end


main()
