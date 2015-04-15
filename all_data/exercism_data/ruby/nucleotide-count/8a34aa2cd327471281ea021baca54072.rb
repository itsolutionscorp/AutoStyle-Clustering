class Nucleotide
  def initialize(dna)
    raise ArgumentError if dna !~ /^[ATCG]*$/
    @dna = dna
  end

  def self.from_dna(dna)
    Nucleotide.new(dna)
  end

  def count(s)
    @dna.count(s)
  end

  def histogram
    h = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0 }
    @dna.each_char { |c| h[c] += 1 }
    h
  end
end
