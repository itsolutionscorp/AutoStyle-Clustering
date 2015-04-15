class Complement
  @dna_to_rna = { A: 'U', G: 'C', T: 'A', C: 'G' }
  @rna_to_dna = { A: 'T', G: 'C', U: 'A', C: 'G' }

  def self.of_dna(sequence)
    sequence.each_char.map { |c| @dna_to_rna[c.to_sym] } .join
  end

  def self.of_rna(sequence)
    sequence.each_char.map { |c| @rna_to_dna[c.to_sym] } .join
  end
end
