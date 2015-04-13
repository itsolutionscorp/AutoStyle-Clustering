def compute(string1, string2)
    differences = 0

    string1.length >= string2.length ?  long_string = string1 : long_string = string2
    string1.length >= string2.length ?  short_string = string2 : short_string = string1

    short_string.split("").each_with_index do |gene, i|
      if gene != long_string[i]
        differences += 1
      end
    end

    differences
  end