class Complement

  DNA_NUCLEOTIDES = 'GCTA'
  RNA_NUCLEOTIDES = 'CGAU'

  class << self
    def of_dna strand
      new( strand, DNA_NUCLEOTIDES, RNA_NUCLEOTIDES ).get_complement 
    end

    def of_rna strand
      new( strand, RNA_NUCLEOTIDES, DNA_NUCLEOTIDES ).get_complement 
    end
  end

  attr_reader :strand, :from, :to

  def initialize strand, from, to
    @strand = strand
    @from   = from
    @to     = to
  end

  def get_complement
    strand.tr from, to
  end

end
