def combine_anagrams(words)
  anagrams = Hash.new("")
  words.each do |word|
    if (anagrams[word.downcase.chars.sort.join] == "") then
      anagrams[word.downcase.chars.sort.join] = Array.new
    end
    anagrams[word.downcase.chars.sort.join].push(word)
  end
  result = Array.new
  anagrams.each { |key, value| result.push(value) }
  return result
end

