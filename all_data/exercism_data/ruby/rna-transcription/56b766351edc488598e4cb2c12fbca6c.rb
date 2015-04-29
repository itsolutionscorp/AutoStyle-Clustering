class Complement
  def self.of_dna(nuc)
    comp = ""
    nuc.each_char do |c|
      comp += 'C' if c == 'G'
      comp += 'G' if c == 'C'
      comp += 'A' if c == 'T'
      comp += 'U' if c == 'A'
    end
    comp
  end

  def self.of_rna(nuc)
    comp = ""
    nuc.each_char do |c|
      comp += 'G' if c == 'C'
      comp += 'C' if c == 'G'
      comp += 'A' if c == 'U'
      comp += 'T' if c == 'A'
    end
    comp
  end
end
