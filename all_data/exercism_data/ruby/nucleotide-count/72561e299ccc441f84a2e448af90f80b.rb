class DNA
  DNA_NUCLEOTIDES = ['A', 'T', 'C', 'G']
  NUCLEOTIDES     = DNA_NUCLEOTIDES.dup.push('U')

  def initialize(dna)
    validate_nucleotides(dna, DNA_NUCLEOTIDES)
    @dna = dna
  end

  def count(nucleotide)
    validate_nucleotides(nucleotide, NUCLEOTIDES)
    @dna.size - @dna.delete(nucleotide).size
  end

  def nucleotide_counts
    DNA_NUCLEOTIDES.each_with_object(Hash.new) do |nucleotide, counts|
      counts[nucleotide] = count(nucleotide)
    end
  end

  private

  def validate_nucleotides(dna, valid_set)
    raise ArgumentError unless dna.chars.all? {|char| valid_set.include?(char)}
  end
end
