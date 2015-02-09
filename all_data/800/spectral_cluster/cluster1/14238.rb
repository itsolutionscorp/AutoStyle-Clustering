# Data structure: A hash, key containing sorted letters, and value consisting of an arrarray of the words

def combine_anagrams(words)
  dict = {}
  words.each { |word|
    key = word.downcase.chars.sort.join
    dict[key] ||= []
    dict[key] << word
    #puts "Word: " + word + "; key: " + key + "; dict: " + dict[key].inspect
  }
  output = []
  dict.each { |key, value|
    output << value
  }
  output
end

input = ['cars', 'four', 'poTaToes', 'RACS', 'four','scar', 'creams', 'scream']

combine_anagrams(input).inspect
