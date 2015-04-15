class DNA
  def initialize(dna_string)
    @dna_string = dna_string
    @nucleotides = %W(A C G T U)
    raise ArgumentError if dna_string.include?("ACGU")
  end

  def count(nucleotide)
    raise ArgumentError unless valid?(nucleotide)
    @dna_string.count(nucleotide)
  end

  def nucleotide_counts
    @nucleotides.each_with_object(Hash.new(0)) do |nucleotide, hash|
      hash[nucleotide] = count(nucleotide)
      hash.delete("U")
    end
  end

  private
  def valid?(nucleotide)
    @nucleotides.include? nucleotide
  end

end
