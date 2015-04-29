require 'pry'

class Complement
  DNA_COMPLEMENTS = { 'C' => 'G', 'G' => 'C', 'A' => 'T', 'U' => 'A' }
  RNA_COMPLEMENTS = { 'C' => 'G', 'G' => 'C', 'A' => 'U', 'T' => 'A' }

  def self.of_dna(input)
    input.split('').map { |letter| RNA_COMPLEMENTS[letter] }.join
  end

  def self.of_rna(input)
    input.split('').map { |letter| DNA_COMPLEMENTS[letter] }.join
  end
end
