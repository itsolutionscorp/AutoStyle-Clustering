class DNA < String

  DNA_NUCLEOTIDES = ['A', 'T', 'C', 'G']
  RNA_NUCLEOTIDES = ['A', 'C', 'G', 'U']

  def DNA.valid_dna_sequence?(sequence)
    sequence.chars.all? { |char| DNA_NUCLEOTIDES.include?(char) }
  end

  def DNA.valid_nucleotide?(nucleotide)
    DNA_NUCLEOTIDES.include?(nucleotide) or 
      RNA_NUCLEOTIDES.include?(nucleotide)
  end

  def initialize(sequence)
    raise ArgumentError unless DNA.valid_dna_sequence?(sequence)
    super
  end

  def nucleotide_counts
    DNA_NUCLEOTIDES.each_with_object(Hash.new) do |nucleotide, counts|
      counts[nucleotide] = count(nucleotide)
    end
  end

  def count(nucleotide)
    raise ArgumentError.new unless DNA.valid_nucleotide?(nucleotide)
    super
  end

end
