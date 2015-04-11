class Anagram
  def initialize word
    @word = word.downcase
    @letters = @word.chars.sort
  end

  def match word_array
    word_array.select do |word|
      same_letters?(word) && word.downcase != @word
    end
  end

  private
    def same_letters? word
      word.downcase.chars.sort.eql? @letters
    end
end
