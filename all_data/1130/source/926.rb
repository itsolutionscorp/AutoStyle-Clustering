# input: 
# input = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
  blah = []
  words.each do |word|
    found = false
    blah.each do |anagram|
      if anagram[0].downcase.chars.sort.join == word.downcase.chars.sort.join
        anagram << word
        found = true
      end
    end
    if not found
      blah << [word]
    end
  end
  return blah
end

print combine_anagrams(['hello', 'hello', 'olleh', 'hi', 'ih', 'joy', 'bye'])
# print combine_anagrams([])