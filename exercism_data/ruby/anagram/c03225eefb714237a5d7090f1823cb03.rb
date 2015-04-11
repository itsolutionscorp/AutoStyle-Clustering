class Anagram
  def initialize word
    @word = word.downcase
    @letters = @word.letters_array
  end

  def match word_array
    word_array.select do |word|
      same_letters?(word) && word.downcase != @word
    end
  end

  private
    def same_letters? word
      word.letters_array.eql? @letters
    end
end

class String
  def letters_array
    downcase.chars.sort
  end
end
