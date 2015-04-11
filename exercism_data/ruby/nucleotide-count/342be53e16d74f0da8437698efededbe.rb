class Nucleotide

  HASH = { 'A' => 0, 'T' => 0, 'G' => 0, 'C' => 0 }

  attr_reader :strand

  def initialize strand
    @strand = strand
    check_symbols
  end

  def self.from_dna strand 
    self.new strand 
  end

  def count nucleotide
    strand.chars.count nucleotide
  end

  def histogram
    strand.chars.each_with_object( HASH.clone ) do |nucleotide, hash|
      hash[ nucleotide ] += 1
    end
  end

  private

  def check_symbols
    raise ArgumentError if !!(strand =~ /[^ATGC]/)
  end

end
