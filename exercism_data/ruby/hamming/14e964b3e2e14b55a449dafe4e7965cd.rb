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
    count = 0
    genome_difference = []
    self.sequence.each do |genome|
      genome_difference << genome if self.sequence[count] != compared_string.sequence[count] && (self.sequence[count] != nil && compared_string.sequence[count] != nil)
      count += 1
    end
    genome_difference.count
  end
end