class DNA

  attr_reader :dna_strand

  def initialize(dna_strand)
    @dna_strand = dna_strand.split("")
  end

  def count(letter)
    raise ArgumentError if !nucleotide?(letter)

    dna_strand.count(letter)
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
