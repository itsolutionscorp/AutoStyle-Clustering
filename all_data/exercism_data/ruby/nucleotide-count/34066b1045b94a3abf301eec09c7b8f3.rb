class DNA
  attr_accessor :nucleotide_counts

  NUCLEOTIDES = "ATUCG"
  DNA_NUCLEOTIDES = "ATCG"

  def initialize(dna)
    @dna = dna
    @nucleotide_counts = { 'A' => 0, 'T' => 0, 'C' => 0, 'G' => 0 }
    count_nucleotides
  end

  def count(nucleotide)
    NUCLEOTIDES.include?(nucleotide) || raise(ArgumentError)
    @nucleotide_counts.fetch(nucleotide, 0)
  end

  private

  def count_nucleotides
    @dna.each_char do |n|
      raise(ArgumentError) unless DNA_NUCLEOTIDES.include?(n)
      @nucleotide_counts[n] += 1
    end
  end
end
