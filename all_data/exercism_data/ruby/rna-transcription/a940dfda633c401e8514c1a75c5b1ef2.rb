require 'pry'
class Complement
  @@dna_to_rna = { G: 'C', C: 'G', T: 'A', A: 'U' }
  @@rna_to_dna = { C: 'G', G: 'C', A: 'T', U: 'A' }

  def self.of_dna(c)
    c.chars.map { |n| @@dna_to_rna[n.to_sym] }.join
  end

  def self.of_rna(c)
    c.chars.map { |n| @@rna_to_dna[n.to_sym] }.join
  end
end
