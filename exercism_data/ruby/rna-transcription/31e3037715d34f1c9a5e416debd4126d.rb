class Complement
  CONVERSION = {  'G' => 'C', 
                    'C' => 'G',
                    'T' => 'A',
                    'A' => 'U'
  }

  def self.of_dna(strand)
    convert(strand, CONVERSION)
  end

  def self.of_rna(strand)
    convert(strand, CONVERSION.invert)
  end

  def self.convert(strand, conversion)
    strand.chars.inject('') { |result, char| result += conversion[char] }
  end
end
