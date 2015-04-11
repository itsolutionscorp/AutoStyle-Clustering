class Hamming
  def self.compute(strand1, strand2)
    strand1 = Strand.new(strand1)
    strand2 = Strand.new(strand2)
    strand1.get_hamming_difference_between(strand2)
  end
end

class Strand
  attr_accessor :sequence

  def initialize(sequence)
    @sequence = sequence.split(//)
  end

  def get_hamming_difference_between(compared_string)
    i = 0
    number_of_differences = 0
    self.sequence.each do |genome|
      number_of_differences += 1 if self.sequence[i] != compared_string.sequence[i] && (self.sequence[i] != nil && compared_string.sequence[i] != nil)
      i += 1
    end
    number_of_differences
  end
end
