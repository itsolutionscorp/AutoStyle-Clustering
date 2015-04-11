class DNA

  attr_reader :sequence

  def initialize(sequence)
   @sequence = sequence
   raise ArgumentError unless validated_dna
  end

  def count(nucleotide)
   raise ArgumentError unless nucleotides.include?(nucleotide)
   sequence.count(nucleotide)
  end

  def nucleotide_counts
    dna_nucleotides.each_with_object({}) do |nucleotide, counts|
      counts[nucleotide] = count(nucleotide)
    end
  end

  def validated_dna
   sequence.chars.all? do |char|
     dna_nucleotides.include?(char)
   end
  end

  def dna_nucleotides
    ['A','T','C','G']
  end

  def nucleotides
    ['A','T','C','G','U']
  end
end
