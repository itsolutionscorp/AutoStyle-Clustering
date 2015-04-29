class DNA
  DNA_NUCLEOTIDES = %w(A C G T)
  RNA_NUCLEOTIDES = %w(A C G U)
  NUCLEOTIDES = DNA_NUCLEOTIDES + RNA_NUCLEOTIDES

  attr_accessor :dna_string, :nucleotide_counts

  def initialize(dna_string)
    @dna_string = dna_string
    @nucleotide_counts = DNA_NUCLEOTIDES.each_with_object({}){|n,d| d[n] = 0 }
    build_counts
  end

  def count(nucleotide)
    unless NUCLEOTIDES.include? nucleotide
      raise ArgumentError, "#{nucleotide} is not a valid nucleotid"
    end
    nucleotide_counts.fetch(nucleotide){ 0 }
  end

  private

  def build_counts
    dna_string.chars.map do |nucleotide|
      unless DNA_NUCLEOTIDES.include? nucleotide
        raise ArgumentError, "#{nucleotide} is not a valid RNA nucleotide"
      end
      nucleotide_counts[nucleotide] += 1
    end
  end
end
