class DNA

  attr_reader :dna_string

  def initialize(dna_string)
    @dna_string = dna_string
    raise ArgumentError unless validated_dna_nucleotides
  end

  def count(nucleotide)
    if all_dna_and_rna_nucleotides.include? nucleotide
      dna_string.chars.count(nucleotide)
    else
      raise ArgumentError
    end
  end

  def nucleotide_counts
    all_dna_nucleotides.each_with_object(Hash.new) do |nucleotide, hash|
      hash[nucleotide] = count(nucleotide)
    end
  end

  def all_dna_nucleotides
    ['A', 'T', 'C', 'G']
  end

  def all_dna_and_rna_nucleotides
    ['A', 'T', 'C', 'G', 'U']
  end

  def validated_dna_nucleotides
    dna_string.chars.all? {|nucleotide| validated?(nucleotide)}
  end

  def validated?(nucleotide)
    all_dna_nucleotides.include? nucleotide
  end

end
