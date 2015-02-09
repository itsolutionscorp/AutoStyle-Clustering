def combine_anagrams(words)
  result = Hash.new
  words.collect! do |word|
    # key is sorted
    key = word.downcase.chars.sort.join
    if result.has_key? key
      result[key].push(word)
    else
      # insert new hash for series of anagram words
      result[key] = Array.new(1) {word};
    end
  end
  result.values;
end;

test_words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'];
# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
print combine_anagrams(test_words);
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]