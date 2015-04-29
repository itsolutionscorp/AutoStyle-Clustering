require 'pp'

class Complement
  attr_accessor :dna_to_rna_mapping, :rna_to_dna_mapping
  @dna_to_rna_mapping = {
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U"
  }

  @rna_to_dna_mapping = @dna_to_rna_mapping.invert

  def self.of_dna(seq)
    self.convert(seq, @dna_to_rna_mapping)
  end

  def self.of_rna(seq)
    self.convert(seq, @rna_to_dna_mapping)
  end

  def self.convert(seq, mapping)
    seq.each_char.reduce("") do |memo, char|
      memo + mapping[char]
    end
  end
end
