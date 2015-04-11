class Anagram
  def initialize(source)
    @source = source
  end

  def match(words)
    words.select { |word| anagram?(word) }
  end

  private

  def anagram?(word)
    @source.downcase != word.downcase &&
      chars(@source) == chars(word)
  end

  def chars(word)
    word.downcase.chars.sort
  end
end
