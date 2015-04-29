class DNA
  def initialize(nucleotides)
    raise ArgumentError unless nucleotides.chars.all?(&method(:dna_nucleotide?))
    @nucleotides = nucleotides
  end

  def nucleotide_counts
    Hash[DNA_NUCLEOTIDES.map { |nucl| [nucl, count(nucl)] }]
  end

  def count(nucl)
    raise ArgumentError unless dna_or_rna_nucleotide?(nucl)
    @nucleotides.chars.count(nucl)
  end

  private

  DNA_NUCLEOTIDES = %w[A C G T]
  RNA_NUCLEOTIDES = %w[A C G U]

  def dna_nucleotide?(nucl)
    DNA_NUCLEOTIDES.include?(nucl)
  end

  def rna_nucleotide?(nucl)
    RNA_NUCLEOTIDES.include?(nucl)
  end

  def dna_or_rna_nucleotide?(nucl)
    dna_nucleotide?(nucl) || rna_nucleotide?(nucl)
  end
end
