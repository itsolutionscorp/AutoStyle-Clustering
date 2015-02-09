# --- Comine Anagrams
def combine_anagrams(words)
    anagrams = []
    
  if words.length == 0
    return anagrams
  end

  until words.empty?
    word = words.first
    anagrams.push(words.select {|match| word.downcase.chars.sort.join.eql?(match.downcase.chars.sort.join)})
    words.delete_if{|match| word.downcase.chars.sort.join.eql?(match.downcase.chars.sort.join)}
  end

  return anagrams
end

def sort_letters_of(words)
  sorted_words = Array.new
  words.length.times do |i|
    word = words[i].downcase
    #puts word.to_s
    word = word.chars.sort.join
    #puts word.to_s
    sorted_words[i] = word
    #puts word.sort
  end
  return sorted_words
end

# --- Test Combine Anagrams
#input = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
#input = []
#result = combine_anagrams(input)
#puts result.to_s
