class Anagram

  def initialize word
    @word = word.downcase
  end

  def match words
    words.select { |word| is_anagram? word.downcase }
  end

private

  def is_anagram? sample
    sample.chars.sort == @word.chars.sort && sample != @word
  end

end
