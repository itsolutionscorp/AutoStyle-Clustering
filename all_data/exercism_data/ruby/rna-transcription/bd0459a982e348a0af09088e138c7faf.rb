class Complement
  def self.of_dna(dna)
    n = dna.size
    rna = ""
    (0..n).each do |n|
      if dna[n] == 'A'
        rna << 'U'
      end
      if dna[n] == 'T'
        rna << 'A'
      end
      if dna[n] == 'C'
        rna << 'G'
      end
      if dna[n] == 'G'
        rna << 'C'
      end
    end

    return rna


  end

  def self.of_rna(rna)
    n = rna.size
    dna = ""
    (0..n).each do |n|
      if rna[n] == 'U'
        dna << 'A'
      end
      if rna[n] == 'A'
        dna << 'T'
      end
      if rna[n] == 'C'
        dna << 'G'
      end
      if rna[n] == 'G'
        dna << 'C'
      end
    end

    return dna


  end
end
