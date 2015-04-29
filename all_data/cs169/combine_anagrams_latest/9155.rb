
# input:
# inp = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter

def combine_anagrams(words)

  return [] if words.length == 0
  return [words] if words.length == 1

  remains = []
  check = words[0]
  ana = [check]
  
  words[1..-1].each do |word|
    if check.upcase.split(//).sort == word.upcase.split(//).sort
      ana << word
    else
      remains << word
    end
  end

  [ ana ] + combine_anagrams(remains)
end
