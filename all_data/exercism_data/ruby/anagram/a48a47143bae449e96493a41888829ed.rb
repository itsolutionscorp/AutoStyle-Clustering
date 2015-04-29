class Anagram
  def initialize(word)
    @original_word = word.downcase
  end

  def match(words)
    words.select { |word| anagram? word }
  end

  private

  def anagram?(word)
    expression = word.downcase

    unless @original_word == expression
      @anagram ||= sort_letters @original_word
      @anagram == sort_letters(expression)
    end
  end

  def sort_letters(word)
    word.scan(/./).sort.join
  end
end
