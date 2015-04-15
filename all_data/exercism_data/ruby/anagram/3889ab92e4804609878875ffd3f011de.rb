class Anagram

  def initialize(word)
    @word = AnagramWord.new(word)
  end

  def match(possible_anagrams)
    possible_anagrams.select do |possible_anagram|
      word.anagram_of?(AnagramWord.new(possible_anagram))
    end
  end

  private

  attr_reader :word
end

class AnagramWord
  def initialize(subject)
    @subject = subject.upcase
  end

  def anagram_of?(other)
    subject != other.subject and self == other
  end

  def ==(other)
    self.identity == other.identity
  end  
  
  protected

  attr_reader :subject

  def identity
    subject.chars.sort
  end

end
