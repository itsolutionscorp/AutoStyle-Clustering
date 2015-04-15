class Complement
  def self.of_dna(a)
    a.split("").map do |letter|
      letter.dna_complement
    end.join
  end

  def self.of_rna(a)
    a.split("").map do |letter|
      letter.rna_complement
    end.join
  end
end

class String
  def dna_complement
    case self
    when "C"
      "G"
    when "G"
      "C"
    when "T"
      "A"
    when "A"
      "U"
    end
  end

  def rna_complement
    case self
    when "C"
      "G"
    when "G"
      "C"
    when "U"
      "A"
    when "A"
      "T"
    end
  end
end
