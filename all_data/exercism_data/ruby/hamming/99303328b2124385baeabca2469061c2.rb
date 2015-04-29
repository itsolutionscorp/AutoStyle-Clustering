class Hamming
  def self.compute(strand1, strand2)
    Strand.new(strand1).distance(Strand.new(strand2))
  end
end

class Strand
  attr_reader :strand
  
  def initialize(strand)
    @strand = strand
  end

  def distance(other_strand)
    nucleotide_pairs = self.pair_with(other_strand)
    nucleotide_pairs.reduce(0) do |sum, pair|
      sum + pair.first.distance(pair.last)
    end
  end
  
  def nucleotides
    strand.chars.map { |letter| Nucleotide.new(letter) }
  end

  def pair_with(other_strand)
    if self.length > other_strand.length
      other_strand.nucleotides.zip(self.nucleotides)
    else
      self.nucleotides.zip(other_strand.nucleotides)
    end
  end

  def length
    nucleotides.length
  end
end

class Nucleotide
  attr_reader :letter
  
  def initialize(letter)
    @letter = letter
  end

  def point_mutation?(other)
    self.letter != other.letter
  end

  def distance(other)
    if point_mutation?(other)
      1
    else
      0
    end
  end
end
