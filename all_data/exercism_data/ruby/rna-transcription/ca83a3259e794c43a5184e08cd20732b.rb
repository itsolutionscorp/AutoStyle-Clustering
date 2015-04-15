class Complement
  attr_accessor :dna

  DNA_COMPLEMENTS = { G: 'C', C: 'G', T: 'A', A: 'U' }
  RNA_COMPLEMENTS = { C: 'G', G: 'C', A: 'T', U: 'A' }

  def self.of_dna(string)
    string.split('').map { |char| DNA_COMPLEMENTS[char.to_sym] }.join
  end

  def self.of_rna(string)
    string.split('').map { |char| RNA_COMPLEMENTS[char.to_sym] }.join
  end

end
