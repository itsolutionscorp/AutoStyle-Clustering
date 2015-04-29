class Complement
  def self.of_dna(dna)
    rna=""
    dna.length.times do |i|
      if dna[i].upcase == 'C'
        rna[i]='G'
      elsif dna[i].upcase == 'G'
        rna[i]='C'
      elsif dna[i].upcase == 'T'
        rna[i]='A'
      elsif dna[i].upcase == 'A'
        rna[i]='U'
      end
    end
    puts rna
    return rna
  end

  def self.of_rna(rna)
    dna=""
    rna.length.times do |i|
      if rna[i].upcase == 'C'
        dna[i]='G'
      elsif rna[i].upcase == 'G'
        dna[i]='C'
      elsif rna[i].upcase == 'U'
        dna[i]='A'
      elsif rna[i].upcase == 'A'
        dna[i]='T'
      end
    end
    puts dna
    return dna
  end



end

Complement.of_dna('agct')
Complement.of_rna('agcu')
