class Hamming
  def self.compute(strand_a, strand_b)
    new(strand_a, strand_b).number_of_nucleotide_differences
  end

  def initialize(strand_a, strand_b)
    @strand_a = Strand.new(strand_a)
    @strand_b = Strand.new(strand_b)
  end

  def number_of_nucleotide_differences
    nucleotide_pairs.count do |nuc_a, nuc_b|
      nuc_a != nuc_b
    end
  end

  private

  def nucleotide_pairs
    strand_a, strand_b = equalise_strand_lengths
    strand_a.nucleotides.zip(strand_b.nucleotides)
  end

  def equalise_strand_lengths
    length = shortest_strand_length
    [@strand_a.shorten(length), @strand_b.shorten(length)]
  end

  def shortest_strand_length
    [@strand_a.length, @strand_b.length].min
  end
end

class Strand
  def initialize(string)
    @string = string
  end

  def shorten(new_length)
    Strand.new(@string[0, new_length])
  end

  def length
    @string.length
  end

  def nucleotides
    @string.chars
  end
end
