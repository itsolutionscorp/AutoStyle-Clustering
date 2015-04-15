class DNA
  DNA_NUCLEOTIDES = %w(A T C G)
  NUCLEOTIDES = DNA_NUCLEOTIDES + ['U']

  def initialize dna
    if valid_dna? dna
      @counts = Hash[NUCLEOTIDES.collect { |key| [key, 0] }]
      count_nucleotides dna
    else
      throw :ArgumentError, 'dna, not a valid DNA string'
    end
  end

  def count nucleotide
    if nucleotide?(nucleotide)
      @counts[nucleotide]
    else
      throw :ArgumentError, 'nucleotide, not a valid dna nucleotide'
    end
  end

  def nucleotide_counts
    select_dna_nucleotides
  end

  private

  def select_dna_nucleotides
    @counts.select { |k| DNA_NUCLEOTIDES.include? k }
  end

  def valid_dna? dna
    !dna.match /[^ATCG]/
  end

  def nucleotide? nucleotide
    NUCLEOTIDES.include? nucleotide
  end

  def count_nucleotides dna
    dna.each_char { |c| @counts[c] += 1 }
  end
end
