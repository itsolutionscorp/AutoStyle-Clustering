class Anagram
  def initialize(word)
    @word       = word
    @characters = word.downcase.each_char.sort
  end

  def match(words)
    words.select do |word|
      anagram?(word.downcase)
    end
  end

  private
    def anagram?(word)
      return if @word == word

      @characters == word.each_char.sort
    end
end
