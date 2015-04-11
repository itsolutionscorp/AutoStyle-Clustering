class Anagram
  def initialize(word)
    @word      = word
    @alphagram = to_alphagram(@word)
  end

  def match(choices)
    choices.select do |choice|
      different_word?(choice) && anagrams?(choice)
    end
  end

  private

  def anagrams?(word)
    to_alphagram(word) == @alphagram
  end

  def different_word?(word)
    word.downcase != @word.downcase
  end

  def to_alphagram(word)
    word.downcase.chars.sort
  end
end
