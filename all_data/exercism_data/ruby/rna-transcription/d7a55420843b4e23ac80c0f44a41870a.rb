class Complement
  def self.of_dna(dna_strand)
    rna = ''
    dna_strand.split('').each do |tide|
      rna += 'C' if tide == 'G'
      rna += 'G' if tide == 'C'
      rna += 'A' if tide == 'T'
      rna += 'U' if tide == 'A'
    end
    rna
  end

  def self.of_rna(rna_strand)
    dna = ''
    rna_strand.split('').each do |tide|
      dna += 'G' if tide == 'C'
      dna += 'C' if tide == 'G'
      dna += 'T' if tide == 'A'
      dna += 'A' if tide == 'U'
    end
    dna
  end
end
