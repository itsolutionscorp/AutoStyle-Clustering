class Hamming
  def self.compute(a, b) 
    original = a.chars
    other = b.chars
    count = 0
    normalize(original, other)
    #add to count for each difference
    (0...original.length).each do |na| 
        if original[na] != other[na]
          count += 1
        end
    end
    count
  end
  #make the strands the same size for comparison
  def self.normalize(array1, array2)
    if array1.size > array2.size
      array1.pop(array1.size - array2.size)
    elsif array2.size > array1.size
      array2.pop(array2.size - array1.size)
    end 
  end
end
