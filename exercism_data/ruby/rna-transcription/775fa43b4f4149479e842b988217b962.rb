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
    result = ''
    strand.chars.each do |char|
       result += conversion[char]
    end
    result
  end
end
