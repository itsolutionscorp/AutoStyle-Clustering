module Hamming
  def self.compute(strand_one, strand_two)
    hamming_distance = 0

    while true
      strand_one_val = strand_one.slice!(0)
      strand_two_val = strand_two.slice!(0)

      return hamming_distance if strand_one_val.nil? or strand_two_val.nil?

      hamming_distance += 1 if strand_one_val != strand_two_val
    end
  end
end
