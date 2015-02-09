def combine_anagrams(words)
  finder = Proc.new do |match_word, list| 
    list.select { |word| word if(word.downcase.split(//).sort == match_word.downcase.split(//).sort) }
  end
  anagrams = words.map { |word| finder.call(word, words) }.uniq
end

