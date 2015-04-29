def combine_anagrams(words)
  anagrams = Array.new
  word_added = false
  words.each do |word|
    if anagrams.empty? then
      anagrams.push([word])
    else
      anagrams.each do |anagram|
        anagram.each do |anagram_word|
          if word.downcase.chars.sort.join.eql?(anagram_word.downcase.chars.sort.join) then
            anagram.push(word)
            word_added = true
            break
          end
        end
      end
      anagrams.push([word]) if (not word_added)
      word_added = false
    end
  end
  return anagrams
end

