class Anagram
 

 def initialize(word_to_match)
  @word_to_match = word_to_match
 end

 def match(words)
  words.reject do |word|
    format(word) != format(word_to_match) || word.downcase == word_to_match.downcase 
  end
 end
 private
 attr_reader :word_to_match

 def format(word)
  word.downcase.split('').sort.join('')
 end
end
