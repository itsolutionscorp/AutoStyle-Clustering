class Complement
  MAPPINGS = { 'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U' }

  def self.of_dna(dna)
    to_map(dna, MAPPINGS)
  end
  
  def self.of_rna(rna)
    to_map(rna, MAPPINGS.invert)
  end

  def self.to_map(collection, mappings)
    collection.chars.inject("") do |memo, char|
      memo << mappings[char]
    end
  end
end
