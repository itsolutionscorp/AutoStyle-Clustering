class Anagram
  def initialize(word)
    @word = word
  end

  def match(words)
    words.select{ |subject| anagram?(subject) }
  end

  private
  def anagram?(subject)
    return false if subject.downcase == @word.downcase 
    subject.downcase.chars.sort == @word.downcase.chars.sort
  end
end
