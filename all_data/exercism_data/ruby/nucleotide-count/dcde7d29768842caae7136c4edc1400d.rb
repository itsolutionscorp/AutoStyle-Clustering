class DNA

  attr_reader :dna
  private :dna

  VALID_DNA         = "ACGT"
  VALID_NUCLEOTIDES = VALID_DNA + "U"

  def initialize(dna)
    validate_dna!(dna)
    @dna = dna
  end

  def count(nucleotide)
    validate_nucleotide!(nucleotide)
    nucleotide_counts[nucleotide]
  end

  def nucleotide_counts
    dna.chars.inject(initial_counts) do |h, c|
      h[c] += 1; h
    end
  end

  private

  def initial_counts
    Hash.new(0).merge({"A" => 0, "G" => 0, "C" => 0, "T" => 0})
  end

  def validate_dna!(dna)
    raise ArgumentError, "Invalid DNA!" unless dna =~ /\A[#{VALID_DNA}]*\z/
  end

  def validate_nucleotide!(nucleotide)
    raise ArgumentError, "Invalid nucleotide!" unless nucleotide =~ /\A[#{VALID_NUCLEOTIDES}]*\z/
  end

end
