module Complement
  def self.of_rna(strand)
    complement = []

    strand.split("").each do | nuc |
      complement << get_dna_complement(nuc)
    end

    complement.join("")
  end

  def self.of_dna(strand)
    complement = []

    strand.split("").each do | nuc |
      complement << get_rna_complement(nuc)
    end

    complement.join("")
  end

  def self.get_rna_complement(nucleotide)
    # G -> C
    # C -> G
    # T -> A
    # A -> U
    case nucleotide
    when 'G'
      return 'C'
    when 'C'
      return 'G'
    when 'T'
      return 'A'
    when 'A'
      return 'U'
    end
  end

  def self.get_dna_complement(nucleotide)
    # C -> G
    # G -> C
    # U -> A
    # A -> T
    case nucleotide
    when 'C'
      return 'G'
    when 'G'
      return 'C'
    when 'U'
      return 'A'
    when 'A'
      return 'T'
    end
  end

end
