class Nucleotide
  TYPES = ['A', 'T', 'C', 'G']

  def initialize(dna)
    raise ArgumentError if dna.upcase.index(/[^ATCG]/)

    @dna = dna.upcase
  end

  def count(type)
    @dna.chars.count { |nucleotide| nucleotide == type.upcase }
  end

  def histogram
    TYPES.each_with_object({}) {|type, hash| hash[type] = count(type) }
  end

  def self.from_dna(dna)
    Nucleotide.new(dna)
  end
end
