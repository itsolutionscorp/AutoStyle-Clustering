class Complement
  # Returns *RNA* complement of DNA
  @@DNAtoRNA = {
    'G' => 'C',
    'C' => 'G',
    'T' => 'A',
    'A' => 'U'
  }

  @@RNAtoDNA = {
    'G' => 'C',
    'C' => 'G',
    'U' => 'A',
    'A' => 'T'
  }

  def self.mapper(input_string, converter)
    output = ""
    input_string.chars.each do |char|
      output << converter[char]
    end
    output
  end

  def self.of_dna(dna_str)
    mapper(dna_str,@@DNAtoRNA)
  end

  def self.of_rna(rna_str)
    mapper(rna_str,@@RNAtoDNA)
  end

end
