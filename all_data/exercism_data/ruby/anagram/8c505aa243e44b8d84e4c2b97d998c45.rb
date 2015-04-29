class Anagram 
  def initialize(text)
    @letters = text.downcase.chars.sort
  end

  def match(words)
    words.select { |word| anagram? word.downcase }
  end

  private
  def anagram?(word)
    @letters.eql? word.chars.sort 
  end
end
