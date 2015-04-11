class DNA

  def initialize(dna)
    @dna = valid(dna)
    @nucleotides = {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
  end

  def nucleotide_counts
    counts = @nucleotides.each_key { |key| @nucleotides[key] += count(key.to_s) }
  end

  def count(nucleotide) 
    @dna.count "#{check(nucleotide)}"
  end

  def valid(dna)
    other = dna.gsub(/[ATCG]/, '')
    raise ArgumentError, "#{dna} is not a valid DNA!" unless other.empty?
    dna
  end

  def check(nucleotide)
    ok = ['A', 'T', 'C', 'G', 'U']
    raise ArgumentError, "#{nucleotide} is not a nucleotide!" unless ok.include?(nucleotide)
    nucleotide
  end

end
