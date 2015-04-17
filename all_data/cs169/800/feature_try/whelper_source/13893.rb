def contains_anagram?(array, word)
  array.each { |elt| return true if are_anagrams?(elt, word) }
  false
end

def combine_anagrams(words)
  anagrams = []
  words.each do |word|
    anagram_found = false
    anagrams.each do |elt|
      if contains_anagram?(elt, word) then
        elt.push(word)
        anagram_found = true
        break
      end
    end
    anagrams.push([word]) if (not anagram_found)
  end
  return anagrams
end
