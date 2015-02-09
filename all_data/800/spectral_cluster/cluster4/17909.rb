# Part3 : Anagrams
def combine_anagrams(words)
  cp = 0
  result = Array.new
  hash = Hash.new []
  words.each do |word|
    cp += 1
    (cp..words.count).each do |i|
      if(!hash[word.to_s.downcase.chars.sort.join].include?(word))
        hash[word.to_s.downcase.chars.sort.join] += [word]
      end
      end
  end
  hash.each do |key, value|
    result.push(value)
  end
  return result
end

# Test
input = ['Cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
p combine_anagrams(input)