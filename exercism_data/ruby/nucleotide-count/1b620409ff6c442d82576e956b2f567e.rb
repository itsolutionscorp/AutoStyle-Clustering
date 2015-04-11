class DNA

  DNA_VALIDATOR = /\A[ACGT]*\Z/
  NUCLEOTIDE_VALIDATOR = /\A[ACGTU]\Z/

  def initialize(dna_string)
    raise ArgumentError unless dna_string =~ DNA_VALIDATOR
    @dna_string = dna_string
  end

  def count(char)
    raise ArgumentError unless char =~ NUCLEOTIDE_VALIDATOR
    @dna_string.chars.count(char)
  end

  def nucleotide_counts
    @dna_string.chars.each_with_object(nucleotide_hash) do |char, hsh|
      hsh[char] += 1
    end
  end


  private

  def nucleotide_hash
    {'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0}
  end

end
