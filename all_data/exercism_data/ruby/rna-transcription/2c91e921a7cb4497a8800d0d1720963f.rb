class Complement

  def self.of_dna(dna_nucleotide)
   dna_nucleotide = dna_nucleotide.split('')
   dna_complement = []
   dna_nucleotide.map do |dna|
      case dna
      when 'C'
        dna_complement << 'G'
      when 'G'
        dna_complement << 'C'
      when 'T'
        dna_complement << 'A'
      when 'A'
        dna_complement << 'U'
      end
    end
    dna_complement.join('').to_s
  end

  def self.of_rna(rna_nucleotide)
    rna_nucleotide = rna_nucleotide.split('')
    rna_complement = []
    rna_nucleotide.map do |rna|
      case rna
      when 'G'
        rna_complement << 'C'
      when 'C'
        rna_complement << 'G'
      when 'A'
        rna_complement << 'T'
      when 'U'
        rna_complement << 'A'
      end
    end
    rna_complement.join('').to_s
  end

end
