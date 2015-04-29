class Complement
  def self.complement_replace(str, dict)
    output = ""
    str.each_char do |c|
      output += dict[c.to_sym]
    end
    output
  end

  def self.of_dna(input)
    convert = {G: "C", C: "G", T: "A", A: "U"}
    complement_replace(input, convert)
  end

  def self.of_rna(input)
    convert = {G: "C", C: "G", U: "A", A: "T"}
    complement_replace(input, convert)
  end

end
