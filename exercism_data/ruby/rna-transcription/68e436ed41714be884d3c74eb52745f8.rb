class Complement
  attr_reader :dna_complementers, :dna_complementees, :rna_complementers, :rna_complementees

  def initialize
    @dna_complementers = DNA.values.join
    @dna_complementees = DNA.keys.join
    @rna_complementers = RNA.values.join
    @rna_complementees = RNA.keys.join
  end

  RNA = {"C" => "G",
         "G" => "C",
         "T" => "A",
         "A" => "U"}

  DNA = {"C" => "G",
         "G" => "C",
         "U" => "A",
         "A" => "T"}

  def self.of_dna(letters)
    letters.tr(RNA.keys.join, RNA.values.join)
  end

  def self.of_rna(letters)
    letters.tr(DNA.keys.join, DNA.values.join)
  end
end
