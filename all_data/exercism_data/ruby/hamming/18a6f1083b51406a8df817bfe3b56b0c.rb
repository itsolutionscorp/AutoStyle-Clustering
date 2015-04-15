class Hamming
  def self.compute(strand_one, strand_two)
    arr1 = strand_one.chars
    arr2 = strand_two.chars
    compare(arr1, arr2)
  end

  def self.compare(arr1, arr2)
    hamming_distance = 0
    arr1.each_with_index do |char, index|
      hamming_distance += 1 unless char == arr2[index]
    end
    hamming_distance
  end
end
