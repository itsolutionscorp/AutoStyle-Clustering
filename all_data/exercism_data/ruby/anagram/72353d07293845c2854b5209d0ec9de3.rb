class Anagram
  def initialize(word)
    @word = word
  end

  def match(word_list)
    word_list.clone.keep_if { |word| different_word?(word) && anagram?(word) }
  end

  private

  def different_word?(other_word)
    @word.downcase != other_word.downcase
  end

  def anagram?(other_word)
    prepare(@word) == prepare(other_word)
  end

  def prepare(word)
    word.downcase.chars.sort.join
  end
end
