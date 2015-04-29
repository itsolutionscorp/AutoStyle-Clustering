class Complement
  def self.of_dna(input)

    rna = []
    dna = input.split("")

    dna.each do |strand|
      if strand == "C"
        rna << "G"
      elsif strand == "G"
        rna << "C"
      elsif strand == "T"
        rna << "A"
      elsif strand == "A"
        rna << "U"
      end
    end

    result = rna.join
    return result
  end

  def self.of_rna(input)

    dna = []
    rna = input.split("")

    rna.each do |strand|
      if strand == "C"
        dna << "G"
      elsif strand == "G"
        dna << "C"
      elsif strand == "U"
        dna << "A"
      elsif strand == "A"
        dna << "T"
      end
    end

    result = dna.join
    return result
  end
end
