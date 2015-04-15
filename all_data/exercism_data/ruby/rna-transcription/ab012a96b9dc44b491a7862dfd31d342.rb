class Complement

  def self.of_dna(dna)
    hsh = {G: 'C', C: 'G', T: 'A', A: 'U'}
    dna.split("").each_index do |i|
      dna[i] = hsh[dna[i].to_sym]
    end
    dna
  end

  def self.of_rna(rna)
    hsh = {G: 'C', C: 'G', A: 'T', U: 'A'}
    rna.split("").each_index do |i|
      rna[i] = hsh[rna[i].to_sym]
    end
    rna
  end
end
