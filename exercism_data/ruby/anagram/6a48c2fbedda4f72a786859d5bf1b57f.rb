class Anagram
  attr_reader :word

  def initialize(word)
    @word = AnagramWord.new(word)
  end

  def match(possible_anagrams)
    possible_anagrams.select do |possible_anagram|
      word.anagram_of?(AnagramWord.new(possible_anagram))
    end
  end

end

class AnagramWord
  attr_reader :source

  def initialize(source)
    @source = source.upcase
  end

  def anagram_of?(other)
    self == other if different? other
  end

  def ==(other)
    self.identity == other.identity
  end  
  
  protected

  def identity
    source.chars.sort
  end
  
  private

  def different?(other)
    source != other.source
  end
  
end
