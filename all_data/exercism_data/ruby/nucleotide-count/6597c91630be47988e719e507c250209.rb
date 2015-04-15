class DNA

  attr_reader :strand

  def initialize(strand)
    @strand = strand.chars
  end

  def count(letter)
    raise ArgumentError.new("#{letter} is not a valid nucleotide") if !nucleotide?(letter)

    strand.count(letter)
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

  def nucleotide?(letter)
    (dna_nucleotides + rna_nucleotides).include?(letter)
  end
end
