class DNA

  NUCLEOTIDES = "ATCGU"
  DNA_NUCLEOTIDES = "ATCG"
  attr_reader :sequence

  def initialize dna_sequence
    @sequence = dna_sequence
    check_sequence
  end

  def count nucleotide
    raise ArgumentError if /[^#{NUCLEOTIDES}]/ =~ nucleotide
    @sequence.count(nucleotide)
  end

  def nucleotide_counts
    result = Hash.new(0)
    DNA_NUCLEOTIDES.chars.each { |nucleotide|
      result[nucleotide] = count(nucleotide)
    }
    result
  end

  def check_sequence
    unless @sequence.eql? ""
      raise ArgumentError if /[^#{DNA_NUCLEOTIDES}]/ =~ @sequence
    end
    true
  end

end
