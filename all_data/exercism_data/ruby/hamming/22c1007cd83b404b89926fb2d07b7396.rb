class Hamming
  def self.compute(dna_strand1,dna_strand2)
    @helper = HammingHelper.new(dna_strand1,dna_strand2)
    @helper.number_of_mutation
  end
end

class HammingHelper

  def initialize(dna_strand1, dna_strand2)
    right_length = take_right_length(dna_strand1,dna_strand2)
    @dna_strand1 = dna_strand1[0..right_length-1]
    @dna_strand2 = dna_strand2[0..right_length-1]
  end

  def number_of_mutation
    count = 0
    take_parts_of_strands.each do |pair|
      count += 1 if pair[0] != pair[1]
    end
    count
  end

  def take_parts_of_strands
    splittedStrand1 = @dna_strand1.split("")
    splittedStrand2 = @dna_strand2.split("")
    splittedStrand1.zip(splittedStrand2)
  end

  def is_any_mutation_point?
    @dna_strand1 != @dna_strand2
  end

  def take_right_length(dna_strand1, dna_strand2)
    [dna_strand1.length,dna_strand2.length].min
  end
end
