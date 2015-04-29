class Nucleotides
  def self.dna
    [ "A", "T", "C", "G" ]
  end

  def self.rna
    [ "A", "U", "C", "G" ]
  end

  def self.valid? nucleotide
    (dna + rna).include? nucleotide
  end

  def self.counter type
    self.public_send(type).each_with_object({}) { |n, acc| acc[n] = 0 }
  end
end

class DNA
  def initialize strand
    @strand = strand
  end

  def nucleotide_counts
    nucleotides.each_with_object(counter) { |n, acc| acc[n] += 1 }
  end

  def count nucleotide
    raise ArgumentError.new("No such nucleotide: #{nucleotide}") unless Nucleotides.valid? nucleotide
    nucleotide_counts.fetch nucleotide, 0
  end

  private
  attr_reader :strand

  def counter
    Nucleotides.counter "dna"
  end

  def nucleotides
    strand.chars
  end
end
