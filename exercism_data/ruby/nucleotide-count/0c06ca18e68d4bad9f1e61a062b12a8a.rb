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
    @dna.chars.each_with_object(Hash['ATCG'.chars.zip([0] * 4)]) do |c, hash|
      hash[c] += 1
    end
  end
end
