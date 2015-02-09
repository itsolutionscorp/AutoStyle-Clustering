def alpha_order(word)
  word.downcase.chars.sort.join
end

def combine_anagrams(words)
  result = {}

  words.each do |word|
    key = alpha_order(word)
    if result.has_key? key
      result[key].push word
    else
      result[key] = [word]
    end
  end    
  result.values
end
