def compute(character_one, character_two)
    distance = 0
    zip = character_one.chars.zip character_two.chars
    zip.each{ |arr| distance+=1 unless arr.first==arr.last }
    return distance
  end