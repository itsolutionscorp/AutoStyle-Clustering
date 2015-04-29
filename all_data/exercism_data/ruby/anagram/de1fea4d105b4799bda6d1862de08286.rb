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
    sort_chars(candidate) == sort_chars(@word) &&  @word != candidate
  end
  
  def sort_chars(word)
    word.chars.sort
  end
end
