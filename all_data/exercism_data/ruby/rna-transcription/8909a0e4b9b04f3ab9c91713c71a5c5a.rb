class Complement

  DNA_COMPLEMENTS = { G: 'C', C: 'G', T: 'A', A: 'U' }
  RNA_COMPLEMENTS = { C: 'G', G: 'C', A: 'T', U: 'A' }

  def self.of_dna(dna)
    dna.chars.map { |base| DNA_COMPLEMENTS[base.to_sym] }.join
  end

  def self.of_rna(rna)
    rna.chars.map { |base| RNA_COMPLEMENTS[base.to_sym] }.join
  end

end
