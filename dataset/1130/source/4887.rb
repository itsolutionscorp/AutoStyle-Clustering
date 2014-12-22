def count_word(word)
  result = Hash.new 0
  word.downcase.each_char {|x| result[x] += 1}
  result
end

def anagram?(array_or_word, w2)
  word = array_or_word.kind_of?(Array) ? array_or_word[0] : array_or_word
  return count_word(word) == count_word(w2)
end
  
def combine_anagrams(words)
  results = []
  words.each do |word|
    found = false
    results.each do |result| 
      if anagram?(result, word)
        result << word
        found = true
      end
    end
    results << [word] unless found
  end
  results
end

