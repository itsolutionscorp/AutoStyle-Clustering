class DNA
  DNA_NUCLEOTIDES = %w(A T C G)
  RNA_NUCLEOTIDES = %w(A C G U)
  ALL_NUCLEOTIDES = DNA_NUCLEOTIDES + RNA_NUCLEOTIDES

  attr_reader :string

  def initialize(string)
    @string = validate(DNA_NUCLEOTIDES, string)
  end

  def count(nucleotide)
    string.count(validate(ALL_NUCLEOTIDES, nucleotide))
  end
  
  def nucleotide_counts
    DNA_NUCLEOTIDES.inject({}) do |nucleotides, nucleotide|
      nucleotides[nucleotide] = count(nucleotide)
      nucleotides
    end
  end

  private

  def validate(collection, subject)
    subject.tap do |subject|
      subject.chars { |c| raise ArgumentError unless collection.include?(c) }
    end
  end
end
