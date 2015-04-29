class Anagram
  def initialize(word)
    @word    = word.downcase
    @letters = sorted_characters(@word)
  end

  def match(choices)
    choices.select do |choice|
      next if choice.downcase == @word
      sorted_characters(choice.downcase) == @letters
    end
  end

  private

  def sorted_characters(word)
    word.chars.sort
  end
end
