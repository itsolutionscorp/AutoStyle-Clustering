class Complement

  def self.of_dna(strand)
  	complement = []
    strand.each_char { |i| complement.push(dna_to_rna(i)) }
    complement.join
  end
  
  def self.of_rna(strand)
  	complement = []
    strand.each_char { |i| complement.push(rna_to_dna(i)) }
    complement.join
  end

  def self.dna_to_rna(nucleotide)
    case
      when nucleotide == 'G' then 'C'
      when nucleotide == 'C' then 'G'
      when nucleotide == 'T' then 'A'
      when nucleotide == 'A' then 'U'
    end
  end

  def self.rna_to_dna(nucleotide)
    case
      when nucleotide == 'C' then 'G'
      when nucleotide == 'G' then 'C'
      when nucleotide == 'U' then 'A'
      when nucleotide == 'A' then 'T'
    end
  end
end
