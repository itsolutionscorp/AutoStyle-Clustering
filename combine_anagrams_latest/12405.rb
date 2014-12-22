# Part 3: Anagrams
# Matt Holloway
# sample input = ['cars', 'for', 'potatoes', 'racs', 'four', 'scar', 'creams', 'scream']
def combine_anagrams(words)
  output = []
  if words.length == 0
    return output
  end
  words.each do |item|
    s_string = item.downcase.chars.sort.join.to_s
    temp_arry = []
    words.each do |scan|
      if s_string == scan.downcase.chars.sort.join.to_s
        temp_arry << scan
      end
    end
    if output.include?(temp_arry)
      #do nothing
    else
      output << temp_arry
    end
  end
  return output
end