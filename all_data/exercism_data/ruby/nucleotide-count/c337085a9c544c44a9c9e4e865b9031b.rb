class DNA
  def initialize(dna_string)
    validate_dna(dna_string)
    @counts = NucleotideCount.new(dna_string)
  end

  def count(nucleotide)
    @counts.count(nucleotide)
  end

  def nucleotide_counts
    dna_counts = Hash.new(0)
    dna_counts['A']=@counts.count('A')
    dna_counts['T']=@counts.count('T')
    dna_counts['C']=@counts.count('C')
    dna_counts['G']=@counts.count('G')
    dna_counts
  end

  private
  def validate_dna(nucleotide_chain)
    if nucleotide_chain =~ /[^GACT]/
      throw :ArgumentError
    end
  end
end

class NucleotideCount
  def initialize(nucleotide_chain)
    @nucleotide_string = nucleotide_chain
  end

  def count(nucleotide)
    validate_nucleotide(nucleotide)
    @nucleotide_string.count(nucleotide)
  end

  private
  def validate_nucleotide(nucleotide)
    throw :ArgumentError unless nucleotide =~ /[GACTU]/
  end
end
