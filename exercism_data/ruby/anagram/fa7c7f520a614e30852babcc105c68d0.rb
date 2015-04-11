class Anagram
  def initialize word
    @word = word.downcase
    @letters = @word.letters_array
  end

  def match word_array
    word_array.delete_if { |word| word.downcase.eql? @word }
    word_array.select { |word| same_letters?(word) }
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
