class DNA
  DNA_NUCLEOTIDES = ['A', 'T', 'C', 'G']
  NUCLEOTIDES     = DNA_NUCLEOTIDES.dup.push('U')

  def initialize(dna)
    if dna.chars.all? {|char| character_included?(char, DNA_NUCLEOTIDES)}
      @dna = dna
    else
      raise ArgumentError
    end
  end

  def count(nucleotide)
    if character_included?(nucleotide, NUCLEOTIDES)
      @dna.size - @dna.delete(nucleotide).size
    else
      raise ArgumentError
    end
  end

  def nucleotide_counts
    DNA_NUCLEOTIDES.each_with_object(Hash.new) do |nucleotide, counts|
      counts[nucleotide] = count(nucleotide)
    end
  end

  private

  def character_included?(character, collection)
    collection.include?(character)
  end
end
