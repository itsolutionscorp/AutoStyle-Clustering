class Anagram
  def initialize(word)
    @word = word
  end

  def match(list)
    list.select do |word|
      letters_for(word) == letters_for(@word) && @word.downcase != word.downcase
    end
  end

private

  def letters_for(word)
    word.downcase.split('').sort
  end
end
