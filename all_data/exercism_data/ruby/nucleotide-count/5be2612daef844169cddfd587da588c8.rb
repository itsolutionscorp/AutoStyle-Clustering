class DNA
  attr_reader :dna

  DNA_NUCLEOTIDES = ['A', 'C', 'G', 'T']
  URACIL = 'U'

  def initialize(dna)
    @dna = dna
  end

  def nucleotides
    DNA_NUCLEOTIDES + [URACIL]
  end

  def is_valid?(nucleotide)
    nucleotides.include? nucleotide
  end

  def count(nucleotide)
    raise ArgumentError.new 'Invalid nucleotide' unless is_valid? nucleotide
    dna.count nucleotide
  end

  def nucleotide_counts
    DNA_NUCLEOTIDES.each_with_object({}).each do |nucleotide, count|
      count[nucleotide] = count nucleotide
    end
  end
end
