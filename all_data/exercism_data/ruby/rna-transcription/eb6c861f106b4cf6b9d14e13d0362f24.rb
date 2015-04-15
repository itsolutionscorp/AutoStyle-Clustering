class Complement
  COMPLEMENT_DICTIONARY = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}

  def self.of_dna(dna_string)
    dna_string.chars.inject("") do |rna, char|
      rna << COMPLEMENT_DICTIONARY[char]
    end
  end

  def self.of_rna(rna_string) 
    rna_string.chars.inject("") do |dna, char|
      dna << COMPLEMENT_DICTIONARY.key(char)
    end
  end
end
