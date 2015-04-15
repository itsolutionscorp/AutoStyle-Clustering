# input:

# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
  result = Hash.new
    words.each  do     |word|
      key =  word.upcase.chars.sort.join
      if result[key].is_a?(Array)
        result[key] << word
      else
        result[key] = Array.new()
        result[key] << word
        end
    end
  return result.values
end


test1 = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']

combine_anagrams test1

