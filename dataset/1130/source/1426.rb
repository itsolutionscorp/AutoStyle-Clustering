# Exercise 1 for SaaS Course
# Part Three

def combine_anagrams(words)
  anagrams = {}
  res = []
  words.each {
    |word|
    key = word.downcase.chars.sort.join
    if anagrams[key] == nil
      anagrams[key] = [word]
    else
      anagrams[key] += [word]
    end
  }
  anagrams.each_value { |v| res += [v]}
  return res
end

# input:
combine_anagrams(['cars', 'For', 'poTatoes', 'racs', 'four','scar', 'creams', 'scream'])
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter

