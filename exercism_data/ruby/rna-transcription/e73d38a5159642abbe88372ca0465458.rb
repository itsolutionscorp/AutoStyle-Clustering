class Complement
  @@mapping = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}  
  
  def self.of_dna data
    self.transcribe_with_mapping data, @@mapping
  end

  def self.of_rna data
    self.transcribe_with_mapping data, @@mapping.invert
  end

  def self.transcribe_with_mapping data, mapping
    data.chars.map { |char|
      mapping[char].nil? ? (raise ArgumentError) : mapping[char]
    }.join
  end
end
