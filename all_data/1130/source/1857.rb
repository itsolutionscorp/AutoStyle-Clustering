def is_anagram?(a, b)
  a_lower = a.downcase
  b_lower = b.downcase
  return a_lower.split('').sort.join == b_lower.split('').sort.join
end

def combine_anagrams(words)
  word_groups = [];
  words.each do |word|
    added = false
    word_groups.each do |word_group|
      if (word_group.is_a?(Array))
        if (is_anagram?(word_group[0], word)) 
          word_group.push(word)
          added = true
        end
      end
    end
    if (!added)
      word_groups.push([word])
    end
  end
  return word_groups
end