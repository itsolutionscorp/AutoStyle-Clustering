class Anagram
  attr_reader :word
  
  def initialize word
    @word = word.downcase
  end
  
  def match wordlist
    wordlist.select { |word| anagram_of?(Anagram.new(word)) }
  end
  
  protected
  def sorted_chars
    @word.chars.sort
  end

  private
  def anagram_of? anagram
    @word != anagram.word &&
    sorted_chars == anagram.sorted_chars
  end

end
