class DNA
  NUCLEOTIDES = /[ACGTU]+/

  def initialize(input)
    raise ArgumentError if invalid_dna_string? input
    @dna_string = input
  end

  def count(nucleotide)
    raise ArgumentError if invalid_nucleotide? nucleotide
    @dna_string.count nucleotide
  end

  def nucleotide_counts
    {
      'A' => count('A'),
      'T' => count('T'),
      'C' => count('C'),
      'G' => count('G')
    }
  end

  private

  def invalid_dna_string?(input)
    return if input.empty?
    rna?(input) || invalid_nucleotide?(input)
  end

  def invalid_nucleotide?(nucleotide_input)
    nucleotide_input !~ NUCLEOTIDES
  end

  def rna?(input)
    input.include? 'U'
  end
end
