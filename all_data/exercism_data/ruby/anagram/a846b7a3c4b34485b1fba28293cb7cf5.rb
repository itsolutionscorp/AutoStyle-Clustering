class Anagram
  attr_reader :word
  
  def initialize word
    @word = word
  end
  
  def match wordlist
    wordlist.each_with_object([]) do |word, matches|
      matches << word if self.anagram_of?(Anagram.new(word))
    end
  end
  
  def anagram_of? anagram
    return false if @word.downcase == anagram.word.downcase
    @word.downcase.chars.sort == anagram.word.downcase.chars.sort
  end
end
