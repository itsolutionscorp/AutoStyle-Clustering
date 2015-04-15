require 'pry'
class Complement
  DNA_MAP = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }
  RNA_MAP = { 'G' => 'C', 'C' => 'G', 'U' => 'A', 'A' => 'T' }

  def self.of_dna(dna)
    converter(dna, DNA_MAP)
  end

  def self.of_rna(rna)
    converter(rna, RNA_MAP)
  end

  private

  def self.converter(string, map)
    string.split('').inject('') { |results, t| results += map[t] }
  end

end
