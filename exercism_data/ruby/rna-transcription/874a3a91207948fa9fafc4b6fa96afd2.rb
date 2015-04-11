class Complement

  def self.of_dna(dna)

    dna_to_rna = {
      'A' => 'U',
      'T' => 'A',
      'C' => 'G',
      'G' => 'C'
    }
    
    if dna.length == 1
      dna_to_rna[dna]
    else
      rna = []
      dna.each_char do |letter|
        rna.push(dna_to_rna[letter])
      end
      rna.join('')
    end
    
  end

  def self.of_rna(rna)

    rna_to_dna = {
      'U' => 'A',
      'A' => 'T',
      'G' => 'C',
      'C' => 'G'
    }

    if rna.length == 1
      rna_to_dna[rna]
    else
      dna = []
      rna.each_char do |letter|
        dna.push(rna_to_dna[letter])
      end
      dna.join('')
    end
   
  end

end
