class Nucleotide

  HISTOGRAM = { 'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0 }

  def initialize(strand)
    @strand = strand.chars
  end

  def self.from_dna(strand)
    raise ArgumentError unless strand.scan(/[^ATCG]/).empty?
    new(strand)
  end

  def count(x)
    @strand.select { |s| s == x }.length
  end

  def histogram
    HISTOGRAM.each_with_object({}) { |(k),o| o[k] = count(k) }
  end

end
