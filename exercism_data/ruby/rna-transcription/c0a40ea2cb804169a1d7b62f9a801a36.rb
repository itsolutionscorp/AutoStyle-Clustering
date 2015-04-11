class Complement
# * `G` -> `C`
# * `C` -> `G`
# * `T` -> `A`
# * `A` -> `U`

  def self.of_dna(dna)
    dna.upcase!
    dna.each_char.with_index do |char, i|
      if char == 'C'
        dna[i] = 'G'
      elsif char == 'G'
        dna[i] = 'C'
      elsif char == 'T'
        dna[i] = 'A'
      elsif char == 'A'
        dna[i] = 'U'
      end
    end
    dna
  end

  def self.of_rna(rna)
    rna.upcase!
    rna.each_char.with_index do |char, i|
      if char == 'C'
        rna[i] = 'G'
      elsif char == 'G'
        rna[i] = 'C'
      elsif char == 'U'
        rna[i] = 'A'
      elsif char == 'A'
        rna[i] = 'T'
      end
    end
    rna
  end
end
