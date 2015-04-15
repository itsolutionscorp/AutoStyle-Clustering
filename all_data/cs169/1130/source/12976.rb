def combine_anagrams(words)
  word_anagram_pairs = words.map{ |word| [ word, word.downcase.chars.sort.join ] }
  unique_anagrams = word_anagram_pairs.map{ |pair| pair[1] }.uniq
  output = Array.new
  unique_anagrams.each do |anagram|
    output.push( word_anagram_pairs.select{ |pair| pair[1] == anagram } .map{ |pair| pair[0] } )
  end
  output
end
