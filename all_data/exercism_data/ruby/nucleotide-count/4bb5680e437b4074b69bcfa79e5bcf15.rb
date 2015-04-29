class DNA
  VALID_DNA_NUCLEOTIDES = ["A", "C", "G", "T"]
  VALID_NUCLEOTIDES = VALID_DNA_NUCLEOTIDES + ["U"] # you can add arrays together and get back a single array

  def initialize(dna)
    @nucleotides = separate_nucleotides(dna.upcase)
  end

  def count(nucleotide)
    raise ArgumentError unless VALID_NUCLEOTIDES.include?(nucleotide)
    @nucleotides[nucleotide] || 0
  end

  def nucleotide_counts
    @nucleotides
  end

private

  def separate_nucleotides(string)

    hash = VALID_DNA_NUCLEOTIDES.each_with_object({}) do |nucleotide, hash|
      hash[nucleotide] = 0
    end

    string.each_char do |letter|
      raise ArgumentError unless VALID_DNA_NUCLEOTIDES.include?(letter)
      hash[letter] += 1
    end

    hash

  end

end
