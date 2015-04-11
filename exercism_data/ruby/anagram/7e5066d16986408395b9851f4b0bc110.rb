class Anagram

  def initialize(seed)
    @seed = seed.downcase;
  end

  def match(words)
    words.select { | word | anagram?(word) } 
  end

  private
    def anagram?(word)
      word.downcase.chars.sort == @seed.chars.sort && word.downcase != @seed
    end
end
