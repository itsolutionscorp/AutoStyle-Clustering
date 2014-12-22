def combine_anagrams(words)
  anagrams = []
  words.each do |word|
    is_found = false
    anagrams.each_with_index do |group, index|
      if is_anagram(group[0], word) then
        anagrams[index].push(word)
        is_found = true
      end
    end
    anagrams.push([word]) if (not is_found)
  end
  return anagrams
end