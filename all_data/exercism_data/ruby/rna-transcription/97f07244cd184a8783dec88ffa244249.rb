require "pry"
class Complement
  def self.of_dna(letters)
    rna = ""
    letters.each_char do |letter|
      case letter
      when "G"
        rna << "C"
      when "C"
        rna << "G"
      when "A"
        rna << "U"
      when "T"
        rna << "A"
      end
    end
    rna
  end

  def self.of_rna(letters)
    dna = ""
    letters.each_char do |letter|
      case letter
      when "G"
        dna << "C"
      when "C"
        dna << "G"
      when "A"
        dna << "T"
      when "U"
        dna << "A"
      end
    end
    dna
  end
end
