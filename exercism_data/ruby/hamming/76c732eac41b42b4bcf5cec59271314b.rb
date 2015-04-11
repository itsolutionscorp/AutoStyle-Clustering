module Hamming
  def self.compute(strand1, strand2)
    arr1 = strand1.chars
    arr2 = strand2.chars

    differences = 0
    arr1.each_with_index do |char, index|
      differences += 1 unless char == arr2[index]
    end
    differences
  end
end
