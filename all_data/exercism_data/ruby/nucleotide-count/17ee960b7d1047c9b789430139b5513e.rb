class DNA < Struct.new(:sequence)
  URACIL = 'U'
  DNA_NUCLEOTIDES = %w{A T C G}.freeze
  ALL_NUCLEOTIDES = DNA_NUCLEOTIDES.dup.push(URACIL)

  def initialize(sequence)
    raise ArgumentError unless valid_dna_sequence?(sequence)
    super
  end

  def nucleotide_counts
    DNA_NUCLEOTIDES.each_with_object(Hash.new) do |nucleotide, hash|
      hash[nucleotide] = count(nucleotide)
    end
  end

  def count(nucleotide)
    raise ArgumentError unless valid_nucleotide?(nucleotide)
    sequence.count(nucleotide)
  end

  private
  def valid_nucleotide?(nucleotide)
    ALL_NUCLEOTIDES.include?(nucleotide) || nucleotide.empty?
  end

  def valid_dna_sequence?(sequence)
    sequence.each_char.all? { |nucleotide| valid_nucleotide?(nucleotide) } && !sequence.include?(URACIL)
  end
end
