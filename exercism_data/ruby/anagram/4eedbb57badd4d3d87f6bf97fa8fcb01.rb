class Anagram
  def initialize(word)
    @original_word = word.downcase
  end

  def match(words)
    words.select do |word|
      expression = word.downcase

      anagram? expression unless @original_word == expression
    end
  end

  private

  def anagram?(word)
    (@anagram ||= sort_letters @original_word) == sort_letters(word)
  end

  def sort_letters(word)
    word.scan(/./).sort.join
  end
end
