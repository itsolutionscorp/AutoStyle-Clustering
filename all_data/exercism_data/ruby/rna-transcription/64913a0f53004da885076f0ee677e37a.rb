require 'pry'

        class Complement
  def self.of_dna(var)
    formatted_correctly_array = var.chars

    match =
    {
      "C" => "G",
      "G" => "C",
      "T" => "A",
      "A" => "U"
    }
 
    to_return = ""

    formatted_correctly_array.each do |variable|
      to_return << match.fetch(variable)
    end
    to_return
  end

  def self.of_rna(var)
    formatted_correctly_array = var.chars

    match =
    {
      "G" => "C",
      "C" => "G",
      "A" => "T",
      "U" => "A"
    }

   to_return = ""

    formatted_correctly_array.each do |variable|
      to_return << match.fetch(variable)
    end
    to_return
  end
end
Complement.of_dna("ACGTGGTCTTAA")
