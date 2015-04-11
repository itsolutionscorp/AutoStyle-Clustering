class DNA
  attr_reader :sequence
  URACIL = 'U'
  DNA_NUCLEOTIDES = %w{A T C G}.freeze
  ALL_NUCLEOTIDES = DNA_NUCLEOTIDES.dup.push(URACIL)

  def initialize(sequence)
    require_valid_dna(sequence)
    @sequence = sequence
  end

  def nucleotide_counts
    DNA_NUCLEOTIDES.each_with_object(Hash.new) do |nucleotide, hash|
      hash[nucleotide] = count(nucleotide)
    end
  end

  def count(nucleotide)
    require_nucleotide(nucleotide)
    sequence.count(nucleotide)
  end

  private
  def valid_nucleotide?(nucleotide)
    ALL_NUCLEOTIDES.include?(nucleotide) || nucleotide.empty?
  end

  def valid_dna_sequence?(sequence)
    sequence.each_char.all? { |nucleotide| valid_nucleotide?(nucleotide) } &&
      !sequence.include?(URACIL)
  end

  def require_valid_dna(sequence)
    required(valid_dna_sequence?(sequence), "Must utilize valid DNA nucleotides: #{DNA_NUCLEOTIDES}")
    #unless valid_dna_sequence?(sequence)
      #raise ArgumentError.new("Must utilize valid DNA nucleotides: #{DNA_NUCLEOTIDES}")
    #end
  end

  def require_nucleotide(nucleotide)
    required(valid_nucleotide?(nucleotide), "Can only count nucleotides: #{ALL_NUCLEOTIDES}")
    #unless valid_nucleotide?(nucleotide)
      #raise ArgumentError.new("Can only count nucleotides: #{ALL_NUCLEOTIDES}")
    #end
  end

  def required(condition, message)
    raise ArgumentError.new(message) unless condition
  end
end
