class DNA
  DNA_NUCLEOTIDES = %w(A C G T)

  NUCLEOTIDES = ['U', *DNA_NUCLEOTIDES]

  def initialize(string)
    string.chars.each(&self.method(:ensure_not_rna))

    @string = string
  end

  def count(nucleotide)
    ensure_valid_nucleotide(nucleotide)

    @string.scan(/#{ nucleotide }/).size
  end

  def nucleotide_counts
    @string.chars.each_with_object(frequencies_hash) { |nucleotide, frequencies|
      frequencies[nucleotide] += 1
    }
  end

  private

  def ensure_not_rna(nucleotide)
    ensure_collection_includes(DNA_NUCLEOTIDES, nucleotide)
  end

  def ensure_valid_nucleotide(nucleotide)
    ensure_collection_includes(NUCLEOTIDES, nucleotide)
  end

  def ensure_collection_includes(collection, nucleotide)
    unless collection.include?(nucleotide)
      fail ArgumentError, "Invalid nucleotide #{ nucleotide }"
    end
  end

  def frequencies_hash
    DNA_NUCLEOTIDES.each_with_object(Hash.new(0)) { |nucleotide, frequencies|
      frequencies[nucleotide] = 0
    }
  end
end
