class DNA
  DNA_NUCLEOTIDES = %w(A T C G)
  NUCLEOTIDES = DNA_NUCLEOTIDES + %w(U)

  def initialize(dna_string)
    raise ArgumentError unless string_is_dna?(dna_string)
    @dna_string = dna_string
  end

  def count(nucleotide)
    raise ArgumentError unless letter_is_nucleotide?(nucleotide)
    nucleotides.count{|x| x == nucleotide}
  end

  def nucleotide_counts
    nucleotides.each_with_object(init_nucleotide_counts) do |nucleotide, counts|
      counts[nucleotide] += 1
    end
  end

  private

  def nucleotides
    @dna_string.chars
  end

  def init_nucleotide_counts
    DNA_NUCLEOTIDES.each_with_object({}) do |nucleotide, counts|
      counts[nucleotide] = 0
    end
  end

  def letter_is_dna_nucleotide?(letter)
    DNA_NUCLEOTIDES.include?(letter)
  end

  def letter_is_nucleotide?(letter)
    NUCLEOTIDES.include?(letter)
  end

  def string_is_dna?(string)
    string.chars.all?{|letter| letter_is_dna_nucleotide?(letter)}
  end
end
