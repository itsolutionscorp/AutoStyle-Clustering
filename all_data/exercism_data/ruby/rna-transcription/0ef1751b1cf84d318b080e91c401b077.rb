class Complement
  def self.of_dna(dna)
    dna.split("").each_index do |i|
      if dna[i] == 'G'
        dna[i] = 'C'
      elsif dna[i] == 'C'
        dna[i] = 'G'
      elsif dna[i] == 'T'
        dna[i] = 'A'
      elsif dna[i] == 'A'
        dna[i] = 'U'
      end
    end
    dna
  end

  def self.of_rna(rna)
    rna.split("").each_index do |i|
      if rna[i] == 'G'
        rna[i] = 'C'
      elsif rna[i] == 'C'
        rna[i] = 'G'
      elsif rna[i] == 'U'
        rna[i] = 'A'
      elsif rna[i] == 'A'
        rna[i] = 'T'
      end
    end
    rna
  end
end
