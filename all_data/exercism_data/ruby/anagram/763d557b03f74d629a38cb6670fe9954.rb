class Anagram

  attr_accessor :word

  def initialize(word)
    @word = word.downcase
  end

  def match(anagrams)
    anagrams.select do |anagram|
      anagrams_of(word).include? anagram.downcase
    end
  end

  private

  def anagrams_of(word)
    word.chars.permutation.map(&:join).reject {|anagram| anagram.eql? word}
  end

end
