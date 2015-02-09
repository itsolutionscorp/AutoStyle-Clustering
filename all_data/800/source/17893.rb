# Marc Rendl Ignacio

# combine anagrams
# anagrams - words that are composed of the same number of each letter

# input:
arr = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#  => output:
#[["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter

# Ruby class methods can be added on the fly! Isn't that amazing? :)
class String
  def sort_characters
    # chars is an enumerator for a string, something like 'each' method
    # sort returns an array of the sorted characters
    # join connects them together again
    self.downcase.chars.sort.join
  end
end

class Array
  def sort_characters
    self.map { |word| word.sort_characters }
  end

  def anagram_groups
    self.sort_characters.uniq
  end
end

@@new_word = 0
def combine_anagrams(words)
  # 1. classify all the kinds of anagram groups
  anagram_groups = words.anagram_groups


  # create result var and 'containers' for each anagram_group in advance
  result = []
  anagram_groups.each { |i| result << [] }

  # puts each word in a container corresponding to an anagram_group that matches
  words.each do |word|
    matching_index = anagram_groups.index(word.sort_characters)
    result[matching_index].push(word)
  end
  result
end

# Debug stuff
# puts 'Combine anagrams'
# puts (combine_anagrams arr).inspect, '==='
# puts (combine_anagrams arr).size, '==='
# puts @@new_word
# puts arr.size