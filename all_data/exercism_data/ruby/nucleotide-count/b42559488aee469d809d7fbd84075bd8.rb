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
  def validate_dna(possible_dna_sequence)
    if possible_dna_sequence =~ /[^GACT]/
      throw :ArgumentError
    end
  end
end

class NucleotideCount
  def initialize(nucleotide_chain)
    @nucleotide_counts = Hash.new(0)
    @nucleotide_counts['A']=nucleotide_chain.count('A')
    @nucleotide_counts['T']=nucleotide_chain.count('T')
    @nucleotide_counts['C']=nucleotide_chain.count('C')
    @nucleotide_counts['G']=nucleotide_chain.count('G')
    @nucleotide_counts['U']=nucleotide_chain.count('U')
  end

  def count(nucleotide)
    validate_nucleotide(nucleotide)
    @nucleotide_counts[nucleotide]
  end

  private
  def validate_nucleotide(nucleotide)
    throw :ArgumentError unless nucleotide =~ /[GACTU]/
  end
end
