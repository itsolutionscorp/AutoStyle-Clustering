# class to analyse DNA strings
class Nucleotide
  NUC_SYMBOLS = %w(A C G T)

  def self.from_dna(dna)
    @dna = dna.chars
    fail ArgumentError unless validate
    self
  end

  def self.validate
    (@dna.uniq - NUC_SYMBOLS).empty?
  end

  def self.count(nucleotide)
    @dna.count nucleotide
  end

  def self.histogram
    NUC_SYMBOLS.each_with_object({}) { |nuc, result| result[nuc] = count(nuc) }
  end
end
