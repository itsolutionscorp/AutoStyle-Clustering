class Anagram
  attr_reader :word
  
  def initialize word
    @word = word.downcase
  end
  
  def match wordlist
    wordlist.select { |word| anagram_of?(Anagram.new(word)) }
  end
  
  private
  def sorted_chars
    @word.chars.sort
  end

  def anagram_of? anagram
    @word != anagram.word &&
    sorted_chars == anagram.send(:sorted_chars)
  end

end
