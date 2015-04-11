class Hamming
  def self.compute(array1,array2)
    hamming_distance = 0
    i = 0
    while i < array1.length do
      if array1[i] != array2[i]
        hamming_distance += 1
      end
      i+=1
    end
    return hamming_distance
  end
end
