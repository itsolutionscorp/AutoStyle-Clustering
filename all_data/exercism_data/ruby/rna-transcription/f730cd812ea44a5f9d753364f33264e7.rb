module Complement
  DNA_MAPPING =
    {"G" => "C",
     "C" => "G",
     "T" => "A",
     "A" => "U"}

  RNA_MAPPING = DNA_MAPPING.invert

  def self.of_dna(seq)
    of_seq(seq, DNA_MAPPING)
  end

  def self.of_rna(seq)
    of_seq(seq, RNA_MAPPING)
  end

  def self.of_seq(seq, mapping)
    seq.each_char.map { |char| mapping.fetch(char) }.join
  end

  private_class_method :of_seq
end
