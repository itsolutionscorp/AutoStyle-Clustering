class Anagram
  def initialize(anagram)
    @original = anagram
    @chars    = anagram.downcase.split('').sort
  end

  def match(words)
    words.select do |word|
      same_letters?(word) && different_word?(word)
    end
  end

  private
  def same_letters?(word)
    word.downcase.split('').sort == @chars
  end
  def different_word?(word)
    word.downcase != @original.downcase
  end
end
