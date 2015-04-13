def compute(string1, string2)
    pairs = string1.split('').zip(string2.split(''))
    pairs.select{ |char1, char2| char1 != char2 }.count
  end