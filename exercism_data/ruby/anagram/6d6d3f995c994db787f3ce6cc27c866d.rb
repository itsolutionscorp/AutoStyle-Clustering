class Anagram
  def initialize(anagram)
    @original = anagram
  end

  def match(words)
    words.select do |word|
      same_letters?(word) && different_word?(word)
    end
  end

  private

  def chars
    @chars ||= @original.downcase.chars.sort
  end

  def same_letters?(word)
    word.downcase.chars.sort == chars
  end

  def different_word?(word)
    word.downcase != @original.downcase
  end
end
