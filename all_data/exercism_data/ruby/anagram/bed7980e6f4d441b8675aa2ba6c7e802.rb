class Anagram
  def initialize(string)
    @string = string.downcase
  end

  def match(array)
    original = @string.split(//).sort
    array.select do |word|
      contents = word.split(//).map(&:downcase).sort
      word if contents == original && word.downcase != @string
    end
  end
end
