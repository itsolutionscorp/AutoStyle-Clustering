class Complement

  Translation = {"G" => "C", "C" => "G", "T" => "A", "A" => "U"}

  def self.of_dna(input)
    output = ""
    input.each_char do |c|
      output += Translation[c]
    end
    output
  end

  def self.of_rna(input)
    output = ""
    input.each_char do |c|
      output += Translation.invert[c]
    end
    output
  end

end
