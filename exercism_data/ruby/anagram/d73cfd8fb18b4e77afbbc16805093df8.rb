class Anagram
  def initialize(word)
    @word = sanitize(word)
  end

  def match(words)
    words.select { |word| is_anagram?(sanitize word) }
  end
  
  private

  def sanitize(word)
    word.downcase.strip
  end

  def is_anagram?(candidate)
    if @word == candidate
      false
    else
      candidate.chars.sort == @word.chars.sort
    end
  end
end
