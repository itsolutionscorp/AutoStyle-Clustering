def combine_anagrams(words)
  wordCount = Hash.new { |hash, key| hash[key] = [] }
  words.each do |word|
    orgWord = word
    sword = word.downcase.chars.sort.join
    puts(sword)
    wordCount[sword].push(orgWord)
  end
  return wordCount.values
end

