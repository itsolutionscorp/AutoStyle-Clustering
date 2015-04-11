class Anagram
  def initialize(word)
    @word       = word
    @characters = components(word)
  end

  def match(words)
    words.select do |word|
      anagram?(word)
    end
  end

  private
    def anagram?(word)
      return if @word == word.downcase

      @characters == components(word)
    end

    def components(word)
      word.downcase.each_char.sort
    end
end
