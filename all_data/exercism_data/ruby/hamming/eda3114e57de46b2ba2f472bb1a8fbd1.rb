class Hamming
  def self.compute strand1, strand2
    one = DNAStrand.new strand1
    two = DNAStrand.new strand2
    DNAUtil.common_length(one, two).times.count do |position_in_strand|
      one.nucleotides[position_in_strand] != two.nucleotides[position_in_strand]
    end
  end
end

class DNAStrand
  def initialize strand
    @strand = strand
  end

  def length
    @length ||= strand.length
  end

  def nucleotides
    @nucleotides ||= strand.chars.to_a
  end

  private
  attr_reader :strand
end

class DNAUtil
  def self.common_length strand1, strand2
    [strand1.length, strand2.length].min
  end
end
