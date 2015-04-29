class Complement
  def self.of_dna(*var)
    list = *var
    stringy = list.join
    formatted_correctly_array = stringy.split(//)

    match =
    {
      "G" => "C",
      "C" => "G",
      "A" => "T",
      "U" => "A"
    }

    to_return = ""

    formatted_correctly_array.each do |variable|
      to_return << match.key(variable)
    end
    to_return
  end

  def self.of_rna(*var)
    list = *var
    stringy = list.join
    formatted_correctly_array = stringy.split(//)

    match =
    {
      "C" => "G",
      "G" => "C",
      "T" => "A",
      "A" => "U"
    }

    to_return = ""

    formatted_correctly_array.each do |variable|
      to_return << match.key(variable)
    end
    to_return
  end
end
