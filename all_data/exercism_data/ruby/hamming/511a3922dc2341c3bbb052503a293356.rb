class DNA
  attr_reader :strand

  def initialize(strand)
    @strand = Strand.new(strand)
  end

  def hamming_distance(dna_strand)
    foreign_strand = Strand.new(dna_strand)
    strand.diff_count(foreign_strand)
  end
end

Strand = Struct.new(:sequence) do
  def nucleotides
    sequence.chars
  end

  def diff_count(another_strand)
    nucleotides.zip(another_strand.nucleotides).count do |original, foreign|
      foreign && original != foreign
    end
  end
end
