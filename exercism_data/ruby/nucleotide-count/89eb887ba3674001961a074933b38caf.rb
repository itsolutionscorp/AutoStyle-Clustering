class Nucleotides
  def self.valid? nucleotide
    nucleotides.include? nucleotide
  end

  def self.dna
    nucleotides - [ "U" ]
  end

  def self.rna
    nucleotides - [ "T" ]
  end

  def self.nucleotides
    [ "A", "C", "G", "T", "U" ]
  end
end

class DNA
  def initialize strand
    @strand = strand
  end

  def nucleotide_counts
    nucleotides.each_with_object(empty_counter) { |n, acc| acc[n] += 1 }
  end

  def count nucleotide
    raise ArgumentError.new("No such nucleotide: #{nucleotide}") unless Nucleotides.valid? nucleotide
    nucleotide_counts.fetch nucleotide, 0
  end

  private
  attr_reader :strand

  def empty_counter
    Hash[Nucleotides.dna.map { |n| [n, 0] }]
  end

  def nucleotides
    strand.chars
  end
end
