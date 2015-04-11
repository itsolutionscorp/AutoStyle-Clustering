class Complement

  DNA_COMPLEMENTS = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'}
  RNA_COMPLEMENTS = DNA_COMPLEMENTS.invert
  
  def self.of_dna(dna)
    rna = ""
    dna.each_char { |c|

      if !DNA_COMPLEMENTS.has_key? c
        raise ArgumentError.new('Invalid DNA strand')
      end
      
      rna <<  DNA_COMPLEMENTS[c]
    }

    rna
  end

  def self.of_rna(rna)

    dna = ""
    rna.each_char { |c|

      if !RNA_COMPLEMENTS.has_key? c
        raise ArgumentError.new('Invalid RNA strand')
      end
      
      dna << RNA_COMPLEMENTS[c]
    }

    dna
  end
    
end
