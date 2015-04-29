#Part 3: anagrams

def combine_anagrams(words)
  results = Hash.new{|h, k| h[k] = []}
  words.each do |word|
    results[word.downcase.chars.sort(&:casecmp).join] << word
  end
  return results.values.sort
end

puts  combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).to_s
puts  combine_anagrams(['a', 'A']).to_s
puts  combine_anagrams(['Hello', 'hello']).to_s