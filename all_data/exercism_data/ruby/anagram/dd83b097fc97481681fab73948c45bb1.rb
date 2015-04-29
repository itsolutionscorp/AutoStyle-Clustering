class Anagram

  def initialize word
    @word = word.downcase
  end

  def match words
    words.each_with_object([]) do |sample, matches|
      matches << sample if is_anagram?(sample.downcase)
    end
  end

private

  def is_anagram? sample
    sample.chars.sort == @word.chars.sort && sample != @word
  end

end
