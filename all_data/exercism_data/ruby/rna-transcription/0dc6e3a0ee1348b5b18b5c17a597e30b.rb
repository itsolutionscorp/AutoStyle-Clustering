class Complement
  def self.of_dna(*var)
    # give the input *var a variable name
    list = *var
    #convert the 'list' array to a string so we can make it an array of 
    #several elements instead of just one.
    stringy = list.join
    #now it is formatted so we can run .each on each element in the array.
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
    # give the input *var a variable name
    list = *var
    #convert the 'list' array to a string so we can make it an array of 
    #several elements instead of just one.
    stringy = list.join
    #now it is formatted so we can run .each on each element in the array.
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
