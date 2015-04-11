class Anagram

  def initialize(word)
    @word = word
  end

  def match(words)
    sorted_word = sort @word
    words.select { |word| @word.downcase != word.downcase && sorted_word == sort(word) }
  end

  private

  def sort(word)
    word.downcase.split(//).sort.join
  end
end
