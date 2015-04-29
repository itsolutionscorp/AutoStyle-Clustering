class Anagram
  def initialize(word)
    @word = sanitize(word)
  end

  def match(words)
    words.select { |word| anagram?(sanitize word) }
  end
  
  private

  def sanitize(word)
    word.downcase.strip
  end

  def anagram?(candidate)
    candidate.chars.sort == @word.chars.sort &&  @word != candidate
  end
end
