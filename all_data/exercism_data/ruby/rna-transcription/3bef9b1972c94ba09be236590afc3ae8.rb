class Complement

  def self.of_dna(strand)
    arr = strand.split("").map do |e|
      case e
        when "G" then "C"
        when "C" then "G"
        when "T" then "A"
        when "A" then "U"
        else raise ArgumentError
      end
    end
    arr.join("")
  end

  def self.of_rna(strand)
    arr = strand.split("").map do |e|
      case e
        when "C" then "G"
        when "G" then "C"
        when "A" then "T"
        when "U" then "A"
        else raise ArgumentError
      end
    end
    arr.join("")
  end
end
