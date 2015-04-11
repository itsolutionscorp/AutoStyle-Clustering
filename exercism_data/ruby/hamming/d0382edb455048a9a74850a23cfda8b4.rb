class Hamming

  def self.compute(array1, array2)
    difference = 0
    shortest_length = array1.length < array2.length ? array1.length : array2.length
    shortest_length.times do |i|
      difference += 1 if array1[i] != array2[i]
    end
    return difference
  end
end
