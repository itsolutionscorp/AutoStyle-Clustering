class Complement

  $dna_complements = {'G' => 'C', 'C' => 'G', 'T' => 'A', 'A' => 'U'};
  $rna_complements = $dna_complements.invert;
  
  def self.of_dna(dna)
    rna = "";
    dna.each_char { |c|

      if !$dna_complements.has_key? c
        raise ArgumentError.new('Invalid DNA strand')
      end
      
      rna <<  $dna_complements[c];
    }

    rna;
  end

  def self.of_rna(rna)

    dna = "";
    rna.each_char { |c|

      if !$rna_complements.has_key? c
        raise ArgumentError.new('Invalid RNA strand')
      end
      
      dna << $rna_complements[c];
    }
    puts dna;
    dna;
  end
    
end
