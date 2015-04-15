class Hamming
  def self.compute(dna_strand1,dna_strand2)
    @helper = HammingHelper.new(dna_strand1,dna_strand2)
    @helper.compute
  end


end




class HammingHelper

  def initialize(dna_strand1, dna_strand2)
    right_length = takeRightLength(dna_strand1,dna_strand2)
    @dna_strand1 = dna_strand1[0..right_length-1]
    @dna_strand2 = dna_strand2[0..right_length-1]
  end

  def compute
    numberOfMutation
  end

  def numberOfMutation
    count = 0
    takePartsOfStrands.each do |pair|
      count += 1 if pair[0] != pair[1]
    end
    count
  end

  def takePartsOfStrands
    splittedStrand1 = @dna_strand1.split("")
    splittedStrand2 = @dna_strand2.split("")
    splittedStrand1.zip(splittedStrand2)
  end

  def isAnyMutationPoint?
    @dna_strand1 != @dna_strand2
  end

  def takeRightLength(dna_strand1, dna_strand2)
    [dna_strand1.length,dna_strand2.length].min
  end
end
