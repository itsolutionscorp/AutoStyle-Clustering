def combine_anagrams(words)
  anagrams = Hash.new { |h, k| h[k] = [] }
  words.each do |word|
    key = word.downcase.chars.sort.join
    puts("word=#{word} key=#{key}")
    anagrams[key].push(word)
  end
  puts(("anagrams: " + anagrams.inspect))
  return anagrams.values
end

