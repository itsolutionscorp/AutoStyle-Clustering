class Hamming
  def self.compute(strand1, strand2)
    pairs = nucleotide_pairs(Strand.new(strand1), Strand.new(strand2))
    pairs.count { |n1, n2| n1.point_mutation?(n2) }
  end

  private

  def self.nucleotide_pairs(strand1, strand2)
    min_length = [strand1.length, strand2.length].min
    strand1.nucleotides.zip(strand2.nucleotides).take(min_length)
  end
end

class Strand
  attr_reader :strand

  def initialize(strand)
    @strand = strand
  end

  def nucleotides
    strand.chars.map { |base| Nucleotide.new(base) }
  end

  def length
    nucleotides.length
  end
end

class Nucleotide
  attr_reader :base

  def initialize(base)
    @base = base
  end

  def point_mutation?(other)
    self.base != other.base
  end
end
