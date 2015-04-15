class DNA

  NUCLEOTIDES = %w(A T C G U)
  DNA_NUCLEOTIDES = %w(A T C G)

  def initialize(dna_string)
    @dna_string = dna_string if valid?(dna_string)
  end

  def count(a_symbol)
    raise ArgumentError unless nucleotide?(a_symbol)
    @dna_string.chars.reduce(0) do |count, a_nuc|
      count+=1 if a_nuc == a_symbol
      count
    end
  end

  def nucleotide_counts
    DNA_NUCLEOTIDES.reduce({}) do |accu, nucleotide|
      accu[nucleotide] = count(nucleotide)
      accu
    end
  end

  private
  def valid?(dna_string)
    dna_string.chars.each{ |d| raise ArgumentError unless dna_nucleotide?(d) }
  end

  def dna_nucleotide?(a_symbol)
    DNA_NUCLEOTIDES.include?(a_symbol)
  end

  def nucleotide?(a_symbol)
    NUCLEOTIDES.include?(a_symbol)
  end


end
