class DNA
  attr_reader :dna

  DNA_NUCLEOTIDES = ['A', 'C', 'G', 'T']
  RNA_NUCLEOTIDES = ['A', 'C', 'G', 'U']

  URACIL = 'U'

  def initialize(dna)
    @dna = dna
  end

  def nucleotides
    DNA_NUCLEOTIDES + RNA_NUCLEOTIDES
  end

  def is_valid?(nucleotide)
    nucleotides.include? nucleotide
  end

  def count(nucleotide)
    raise ArgumentError.new 'Invalid nucleotide' unless is_valid? nucleotide
    dna.chars.count nucleotide
  end

  def nucleotide_counts
    (nucleotides - [URACIL]).each_with_object(Hash.new(0)).each do |nucleotide, count|
      count[nucleotide] = count nucleotide
    end
  end
end
