module Hamming
  def compute(strand1, strand2)
    hamming_distance = 0
    index = 0
    # iterate over strings, compare values, increment Hamming distance if values are not equal
    while index < [strand1.size, strand2.size].min do
      hamming_distance += 1 if strand1[index] != strand2[index]
      index += 1
    end
    return hamming_distance
  end
  module_function :compute
end
