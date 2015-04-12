class Hamming

  def compute(string1, string2)
    @counter = 0
    one = string1.chars[0]
    two = string2.chars[0]
    three = string1.chars[1]
    four = string2.chars[1]
    five = string1.chars[2]
    six = string2.chars[2]
    seven = string1.chars[3]
    eight = string2.chars[3]
    nine = string1.chars[4]
    ten = string2.chars[4]
    eleven = string1.chars[5]
    twelve = string2.chars[5]
    thirteen = string1.chars[6]
    fourteen = string2.chars[6]
    fifteen = string1.chars[7]
    sixteen = string2.chars[7]
    seventeen = string1.chars[8]
    eighteen = string2.chars[8]
    nineteen = string1.chars[9]
    twenty = string2.chars[9]
    twentyone = string1.chars[10]
    twentytwo = string2.chars[10]
    twentythree = string1.chars[11]
    twentyfour = string2.chars[11]
    if one != two
      @counter += 1
    end
    if
      three != four
      @counter += 1
    end
    if
      five != six
      @counter += 1
    end
    if
      seven != eight
      @counter += 1
    end
    if
      nine != ten
      @counter += 1
    end
    if
      eleven != twelve
      @counter += 1
    end
    if
      thirteen != fourteen
      @counter += 1
    end
    if
      fifteen != sixteen
      @counter += 1
    end
    if
      seventeen != eighteen
      @counter += 1
    end
    if
      nineteen != twenty
      @counter += 1
    end
    if
      twentyone != twentytwo
      @counter += 1
    end
    if
      twentytwo != twentythree
      @counter += 1
    end
    @counter
  end
end
