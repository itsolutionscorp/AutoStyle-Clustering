class Nucleotide
  def self.from_dna(strand)
    raise ArgumentError if strand.chars.any? { |n| !'ACTG'.include?(n) }
    new(strand)
  end

  def initialize(strand)
    @strand = strand
  end

  def count(nucl)
    @strand.chars.select { |n| n == nucl }.length
  end

  def histogram
    histogram = { 'A' => 0, 'C' => 0, 'T' => 0, 'G' => 0 }
    @strand.chars.each_with_object(histogram) { |n, h| h[n] += 1 }
  end
end
