class Nucleotide

  def initialize(strand)
    raise ArgumentError if strand.match(/[^AGCT]/)
    @strand = strand
  end

  def self.from_dna(strand)
    new(strand)
  end

  def count(nucleotide)
    @strand.split('').inject(0) do |total, letter|
      total += 1 if letter == nucleotide
      total
    end
  end

  def histogram
    hist = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
    hist.each do |k, v|
      hist[k] = count(k)
    end
    hist
  end

end
