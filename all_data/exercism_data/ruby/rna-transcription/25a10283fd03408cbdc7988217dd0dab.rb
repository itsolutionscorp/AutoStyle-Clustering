class Complement

  def self.of_rna(rna)
    for i in 0..rna.length-1
      case rna[i]
        when 'C' then rna[i] = 'G'
        when 'G' then rna[i] = 'C'
        when 'A' then rna[i] = 'T'
        when 'U' then rna[i] = 'A'
        else puts "Incorrect Rna"
      end
    end
    rna
  end

  def self.of_dna(dna)
   for i in 0..dna.length-1
      case dna[i]
        when 'G' then dna[i] = 'C'
        when 'C' then dna[i] = 'G'
        when 'T' then dna[i] = 'A'
        when 'A' then dna[i] = 'U'
        else puts "Incorrect Dna"
      end
    end
     dna
  end

end
