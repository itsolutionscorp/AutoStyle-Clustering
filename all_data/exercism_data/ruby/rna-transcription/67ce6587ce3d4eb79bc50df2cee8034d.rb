class Complement

  def self.of_dna(strand)
    create_complement(strand, "D")
  end

  def self.of_rna(strand)
    create_complement(strand, "R")
  end

  def self.create_complement(strand)
    complement = []

    strand.each_char do |char|
      case char
      when "G"
        complement << "C"
      when "C"
        complement << "G"
      when "T"
        complement << "A"
      when "A"
        nuke = if type == "R"
          "T"
        else
          "U"
        end
        complement << nuke
      when "U"
        complement << "A"
      end
    end
    complement.join
  end
end
