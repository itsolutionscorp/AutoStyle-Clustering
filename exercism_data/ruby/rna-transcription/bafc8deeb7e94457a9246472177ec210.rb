class Complement
  def self.of_dna(rna)
    result = rna
    rna.size.times do |i|
      if rna[i] == 'G'
        result[i] = 'C'
      elsif rna[i] == 'C'
        result[i] = 'G'
      elsif rna[i] == 'T'
        result[i] = 'A'
      elsif rna[i] == 'A'
        result[i] = 'U'
      end
    end
    result
  end

  def self.of_rna(dna)
    result = dna
    dna.size.times do |i|
      if dna[i] == 'C'
        result[i] = 'G'
      elsif dna[i] == 'G'
        result[i] = 'C'
      elsif dna[i] == 'A'
        result[i] = 'T'
      elsif dna[i] == 'U'
        result[i] = 'A'
      end
    end
    result
  end
end
