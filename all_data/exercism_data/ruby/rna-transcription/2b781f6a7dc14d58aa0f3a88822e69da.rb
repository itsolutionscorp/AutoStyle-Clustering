class Complement
  def self.of_dna(dna)
    dna_letters = dna.split ""
    rna_letters = dna_letters.map do |letter|
      case letter
      when "G"
        "C"
      when "C"
        "G"
      when "T"
        "A"
      when "A"
        "U"
      end
    end
    rna_letters.join ""
  end

  def self.of_rna(rna)
    rna_letters = rna.split ""
    dna_letters = rna_letters.map do |letter|
      case letter
      when "G"
        "C"
      when "C"
        "G"
      when "U"
        "A"
      when "A"
        "T"
      end
    end
    dna_letters.join ""
  end
end
