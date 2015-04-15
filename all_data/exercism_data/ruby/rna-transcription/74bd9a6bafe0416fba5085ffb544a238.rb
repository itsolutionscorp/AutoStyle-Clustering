class Complement
  def self.of_dna(dna_strand)
    dna = dna_strand.split(//)
    rna = []
    dna.each do |nucleotide|
      case nucleotide
        when 'G' then rna.push('C')
        when 'C' then rna.push('G')
        when 'T' then rna.push('A')
        when 'A' then rna.push('U')
      end
    end
    rna.join
  end
  def self.of_rna(rna_strand)
    rna = rna_strand.split(//)
    dna = []
    rna.each do |nucleotide|
      case nucleotide
      when 'C' then dna.push('G')
      when 'G' then dna.push('C')
      when 'A' then dna.push('T')
      when 'U' then dna.push('A')
      end
    end
    dna.join
  end
end
