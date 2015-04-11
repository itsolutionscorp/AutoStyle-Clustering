class Nucleotide
  def self.from_dna(str)
    new(str)
  end

  def initialize(str)
    fail ArgumentError unless valid_dna?(str.to_s)
    @dna = str.to_s
  end

  def count(dna_symbol)
    @dna.chars.count { |e| e == dna_symbol }
  end

  def histogram
    %w(A C G T).each_with_object({}) { |e, a| a[e] = count(e) }
  end

  private

  def valid_dna?(str)
    str =~ /^[ACGT]*$/
  end
end
