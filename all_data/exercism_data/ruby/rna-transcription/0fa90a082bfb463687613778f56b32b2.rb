class Complement

  LOOKUP_CHARACTERS = -> (hash, key) { key.chars.map { |c| hash[c] }.join("") }

  DNA_MAPPING = {
    "C" => "G",
    "G" => "C",
    "T" => "A",
    "A" => "U",
  }
  DNA_MAPPING.default_proc = LOOKUP_CHARACTERS

  RNA_MAPPING = DNA_MAPPING.invert
  # Inverting a hash doesn't copy the default_proc over
  RNA_MAPPING.default_proc = LOOKUP_CHARACTERS

  def self.of_dna(needle)
    DNA_MAPPING[needle]
  end

  def self.of_rna(needle)
    RNA_MAPPING[needle]
  end
end
