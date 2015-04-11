class Hamming
  # "The Hamming distance is only defined for sequences of equal length."?  Not
  # according to the "extra_length" tests.  Going with assumption tests rule.

  def self.compute(strand_one, strand_two)
    point_mutations = 0

    strand_one.split(//).each_with_index do |character, index|
      break if strand_two.slice(index).nil?
      (point_mutations += 1) if strand_two.slice(index) != character
    end

    point_mutations

  end
end
