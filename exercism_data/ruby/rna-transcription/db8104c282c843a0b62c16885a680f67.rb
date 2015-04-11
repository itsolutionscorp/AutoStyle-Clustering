class Complement

  def self.of_dna(nucl)    
    create_complement(nucl, "dna")
  end
  
  def self.of_rna(nucl)
    create_complement(nucl, "rna")
  end
  
  def self.create_complement(nucl, strand)
    if strand == "dna"
      decoder_hash = create_dna_decoder_hash
    else
      decoder_hash = create_rna_decoder_hash
    end
      
    complement = ""
    
    nucl.each_char do |char|
      if decoder_hash.has_key? char
        complement << decoder_hash[char]
      else
        complement << char
      end
    end
    
    complement
  end

  def self.create_dna_decoder_hash
    { 'G' => 'C',
      'C' => 'G',
      'T' => 'A',
      'A' => 'U' }
  end
  
  def self.create_rna_decoder_hash
    { 'G' => 'C',
      'C' => 'G',
      'U' => 'A',
      'A' => 'T' }
  end
end
