# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their # letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
  result = []
  words.collect do |w|
    group = []
    words.each  do |e|
      group << e if e.downcase.chars.sort.join == w.downcase.chars.sort.join 
    end
    result << group
  end
  result.uniq
end