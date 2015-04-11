class DNA

  attr_reader :dna_string, :validated_dna_string

  def initialize(dna_string)
    @dna_string = dna_string
    @validated_dna_string = validated_dna
  end

  def count(nucleotide)
    if validated?(nucleotide) || nucleotide == 'U'
      validated_dna_string.count(nucleotide)
    else
      raise ArgumentError
    end
  end

  def nucleotide_counts
    {
      'A' => count('A'),
      'T' => count('T'),
      'C' => count('C'),
      'G' => count('G')
    }
  end

  def validated?(nucleotide)
    all_dna_nucleotides.include? nucleotide
  end

  def all_dna_nucleotides
    ['A', 'T', 'C', 'G']
  end

  def validated_dna
    if dna_string.chars.any? {|nucleotide| validated?(nucleotide) == false}
      raise ArgumentError
    else
      dna_string.chars
    end
  end

end
