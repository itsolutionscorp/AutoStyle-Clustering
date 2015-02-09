# input:
#a = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#b = [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter

class String
  def sort
    self.chars.sort.join
  end
end

def combine_anagrams(words)
  result = {}
  words.map{|w| w.downcase.sort}.each_with_index do |word, i|
    if result[word]
      result[word] << i
    else
      result[word] = [i]
    end
  end
  result.map{|k,v| v.map{|i| words[i]}}
end

#puts combine_anagrams(a)

#puts combine_anagrams(a) == b
