def combine_anagrams(words)
  anagrams = {}
  words.each do |word|
    word.chomp!
    key = word.downcase.split('').sort!
    anagrams[key] ||= Array.new
    anagrams[key].push(word)
  end
   anagrams.values.each do |word|
    word.join(" ") if word.length > 1
end
 
end