class DNA 
  ALL_NUCLEOTIDES = %w{A C G T U}.freeze
  DNA_NUCLEOTIDES = (ALL_NUCLEOTIDES - ['U']).freeze

  def initialize(chain)
    raise ArgumentError unless valid_dna_chain?(chain)
    @chain = chain
  end

  def count(nucleotide)
    raise ArgumentError unless valid_nucleotide?(nucleotide)
    nucleotide_counts[nucleotide]
  end

  def nucleotide_counts
    @counts ||= count_nucleotides
  end

  private

  def count_nucleotides
    counts = DNA_NUCLEOTIDES.each_with_object(Hash.new(0)) { |n, hash| hash[n] = 0 }
    @chain.chars.each_with_object(counts) { |n, hash| hash[n] += 1 }
  end

  def valid_dna_chain?(chain)
    chain.chars.all? { |c| DNA_NUCLEOTIDES.include? c }
  end

  def valid_nucleotide?(nucleotide)
    ALL_NUCLEOTIDES.include? nucleotide
  end
end
