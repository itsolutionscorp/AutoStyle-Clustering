class Anagram
  def initialize(word)
    @original = word.downcase
  end

  def match(candidates)
    candidates.select { |candidate| anagram? candidate }
  end

  private

  def anagram?(word)
    expression = word.downcase

    alphagram == sort_letters(expression) unless @original == expression
  end

  def alphagram
    @alphagram ||= sort_letters @original
  end

  def sort_letters(word)
    word.chars.sort.join
  end
end
