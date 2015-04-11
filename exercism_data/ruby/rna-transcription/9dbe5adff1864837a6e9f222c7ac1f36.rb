class Complement
  RNA_MAP = { "G" => "C", "C" => "G", "T" => "A", "A" => "U" }

  def self.of_dna(dna)
    dna.each_char.map { |c| c = RNA_MAP[c] }.join
  end

  def self.of_rna(rna)
    rna.each_char.map { |c| c = RNA_MAP.invert[c] }.join
  end
end
