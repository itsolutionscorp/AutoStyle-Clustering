 class Hamming

  attr_reader :dna_strand1, :dna_strand2

  def initialize(dna_strand1, dna_strand2)
    @dna_strand1 = dna_strand1
    @dna_strand2 = dna_strand2
  end

  def self.compute(dna_strand1, dna_strand2)
    new(dna_strand1, dna_strand2).strand_lengths
  end

  def strand_lengths
    strand_chars.count do |c|
     strands_changed?(* c)
    end
  end

  private

  def length_of_Strands
    [@dna_strand1.length, @dna_strand2.length].min - 1
  end

  def strands_changed?(char1, char2)
    char1 != char2
  end

  def strand_chars
    s1.chars.zip(s2.chars)
  end

  def s1
    dna_strand1[0..length_of_Strands]
  end

  def s2
    dna_strand2[0..length_of_Strands]
  end

 end
