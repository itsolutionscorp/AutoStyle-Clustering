class Complement

  # Single Responsibility: Create complent RNA strand from DNA strand

  def self.of_dna(strand)
    rna = []
    strand.each_char do |nucleotid|
      rna << dna_to_rna_nucleotid(nucleotid)
    end
    rna.join("")
  end

  def self.of_rna(strand)
    dna = []
    strand.each_char do |nucleotid|
      dna << rna_to_dna_nucleotid(nucleotid)
    end
    dna.join("")
  end

  private

  def self.dna_to_rna_nucleotid(nucleotid)
    case nucleotid
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

  def self.rna_to_dna_nucleotid(nucleotid)
    case nucleotid
    when 'C'
      return 'G'
    when 'G'
      return 'C'
    when 'A'
      return 'T'
    when 'U'
      return 'A'
    end
  end
end
