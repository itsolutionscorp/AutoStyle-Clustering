class Complement

  LOOKUP_CHARACTERS = -> (hash, key) { key.chars.map { |c| hash[c] }.join("") }

  DNA_MAPPING = {
    "C" => "G",
    "G" => "C",
    "T" => "A",
    "A" => "U",
  }

  RNA_MAPPING = DNA_MAPPING.invert
  DNA_MAPPING.default_proc = RNA_MAPPING.default_proc = LOOKUP_CHARACTERS

  def self.of_dna(needle)
    DNA_MAPPING[needle]
  end

  def self.of_rna(needle)
    RNA_MAPPING[needle]
  end
end
