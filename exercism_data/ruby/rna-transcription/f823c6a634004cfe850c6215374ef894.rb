class Complement

  def self.of_dna(dna)
    rna = Array.new

    for nucleotide in 0...dna.size
      case dna[nucleotide]
      when "G"
        rna[nucleotide] = "C"
      when "C"
        rna[nucleotide] = "G"
      when "T"
        rna[nucleotide] = "A"
      when "A"
        rna[nucleotide] = "U"
      end
    end

    return rna.join("")
  end

  def self.of_rna(rna)
    dna = Array.new

    for nucleotide in 0...rna.size
      case rna[nucleotide]
      when "C"
        dna[nucleotide] = "G"
      when "G"
        dna[nucleotide] = "C"
      when "A"
        dna[nucleotide] = "T"
      when "U"
        dna[nucleotide] = "A"
      end
    end

    return dna.join("")
  end

end
