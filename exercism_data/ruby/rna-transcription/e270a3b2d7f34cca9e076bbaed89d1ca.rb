module Complement
  DNA_TO_RNA_MAP = {
    "G" => "C",
    "C" => "G",
    "T" => "A",
    "A" => "U",
  }

  RNA_TO_DNA_MAP = {
    "C" => "G",
    "G" => "C",
    "A" => "T",
    "U" => "A",
  }

  def self.of_dna sequence
    map_nucleotides(sequence, DNA_TO_RNA_MAP)
  end

  def self.of_rna sequence
    map_nucleotides(sequence, RNA_TO_DNA_MAP)
  end

  private

  def self.map_nucleotides sequence, map
    sequence.chars.map{ |c| map[c] }.join
  end
end
