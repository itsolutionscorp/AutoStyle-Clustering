class DNA

  attr_reader :dna_string

  def initialize(dna_string)
    @dna_string = dna_string.split("")
  end

  def count(letter)
    raise ArgumentError if not_nucleotide?(letter)

    dna_string.count(letter)
  end

  def nucleotide_counts
    dna_nucleotides.each_with_object({}) do |letter, nucleotide_counts|
      nucleotide_counts[letter] = count(letter)
    end
  end

  private

  def dna_nucleotides
    ["A", "T", "C", "G"]
  end

  def rna_nucleotides
    ["U"]
  end

  def not_nucleotide?(letter)
    !dna_nucleotides.include?(letter) && !rna_nucleotides.include?(letter)
  end
end
