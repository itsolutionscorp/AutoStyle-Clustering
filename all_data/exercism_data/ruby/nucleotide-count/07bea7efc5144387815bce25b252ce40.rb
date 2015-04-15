class DNA

  attr_reader :validated_dna_string

  def initialize(dna_string)
    @validated_dna_string = validated_dna(dna_string)
  end

  def count(nucleotide)
    if validated?(nucleotide) || nucleotide == 'U'
      validated_dna_string.count(nucleotide)
    else
      raise ArgumentError
    end
  end

  def nucleotide_counts
    all_dna_nucleotides.each_with_object(Hash.new) do |nucleotide, hash|
      hash[nucleotide] = count(nucleotide)
    end
  end

  def validated?(nucleotide)
    all_dna_nucleotides.include? nucleotide
  end

  def all_dna_nucleotides
    ['A', 'T', 'C', 'G']
  end

  def validated_dna(dna_string)
    if dna_string.chars.any? {|nucleotide| validated?(nucleotide) == false}
      raise ArgumentError
    else
      dna_string.chars
    end
  end

end
