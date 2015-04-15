class Anagram

  def initialize(word)
    @word = word
  end

  def match(choices)
    choices.select(&matches?).reject(&identical?)
  end

  private

  def matches?
    ->(choice) { letters(choice) == required }
  end

  def identical?
    ->(choice) { word.downcase == choice.downcase }
  end

  def required
    @required ||= letters(word)
  end

  def letters(word)
    word.downcase.split('').sort
  end

  attr_reader :word
end
