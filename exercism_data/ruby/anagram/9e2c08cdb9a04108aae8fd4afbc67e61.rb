class Anagram
  def initialize(word)
    @word = word
  end

  def match(list)
  result = []
  list.map() {|word| result << word if is_anagram?(word)}
  result
  end

  private

  def is_anagram?(word)
    if @word.downcase == word.downcase
      return false
    end
    sorted(@word) == sorted(word)
  end

  def sorted(word)
    word.downcase.chars.sort.join
  end
end
