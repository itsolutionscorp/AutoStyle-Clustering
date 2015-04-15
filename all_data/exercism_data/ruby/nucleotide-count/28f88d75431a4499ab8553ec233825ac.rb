class DNA

  attr_reader :dna
  private :dna

  VALID_DNA         = %w{ A G C T }
  VALID_NUCLEOTIDES = VALID_DNA + %w{ U }

  def initialize(dna)
    validate_with!(dna, VALID_DNA)
    @dna = dna
  end

  def count(nucleotide)
    validate_with!(nucleotide, VALID_NUCLEOTIDES)
    nucleotide_counts[nucleotide]
  end

  def nucleotide_counts
    dna.chars.inject(initial_counts) do |h, c|
      h[c] += 1; h
    end
  end

  private

  def validate_with!(dna, valid_chars)
    mismatches = dna.scan(/[^#{valid_chars}.join}]/)
    unless mismatches.empty?
      raise ArgumentError, "Invalid characters: #{mismatches.inspect}", caller
    end
  end

  def initial_counts
    Hash.new(0).merge({"A" => 0, "G" => 0, "C" => 0, "T" => 0})
  end

end
