class Hamming

  def compute(strand1, strand2)

    count  = 0
    array1 = strand1.to_s.split('')
    array2 = strand2.to_s.split('')

    if array1.length > array2.length
      shorter_array = array2
    else
      shorter_array = array1
    end

    for i in 0..(shorter_array.length - 1)
      if array1[i] != array2[i]
        count+=1
      end
    end
    return count
  end
end
