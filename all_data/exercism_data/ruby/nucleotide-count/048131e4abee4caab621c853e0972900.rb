class DNA
  DNA_BASES = %W(A C G T)
  NON_DNA_BASES = %W(U)
  ALL_BASES = DNA_BASES + NON_DNA_BASES

  def initialize(dna)
    @dna = dna
  end

  def count(letter)
    raise ArgumentError if !ALL_BASES.include? letter
    @dna.count letter
  end

  def nucleotide_counts
    DNA_BASES.each_with_object({}) { |letter, counts| counts[letter] = @dna.count letter }
  end
end
