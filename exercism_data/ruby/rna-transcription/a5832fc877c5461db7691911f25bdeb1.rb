class Complement
  def self.of_dna(dna)
    dna.chars
    0.upto(dna.length - 1) do |n|
      case dna[n]
      when 'C'
        dna[n] = 'G'
      when 'G'
        dna[n] = 'C'
      when 'T'
        dna[n] = 'A'
      when 'A'
        dna[n] = 'U'
      end
    end
    @new_dna = dna
  end

  def self.of_rna(rna)
    rna.chars
    0.upto(rna.length - 1) do |n|
      case rna[n]
      when 'C'
        rna[n] = 'G'
      when 'G'
        rna[n] = 'C'
      when 'U'
        rna[n] = 'A'
      when 'A'
        rna[n] = 'T'
      end
    end
    @new_rna = rna
  end
end
