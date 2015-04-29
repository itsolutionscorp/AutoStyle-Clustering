require 'pry'
class Nucleotide
  DNA = ['A', 'T', 'C', 'G']

  def self.from_dna dna
    @dna = permitted_nucleotides(dna)
    self
  end

  def self.count letter
    @dna.count letter
  end

  def self.histogram
    DNA.each_with_object({}) do |nucleotide, result|
      result[nucleotide] = count(nucleotide)
    end
  end

  private

  def self.permitted_nucleotides dna
    if dna.scan(/[^ATCG]/).empty?
      dna
    else
      raise ArgumentError
    end
  end
end
