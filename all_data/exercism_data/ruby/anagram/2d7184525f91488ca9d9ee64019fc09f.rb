class Anagram
  attr_reader :downcase_word

  def initialize(word)
    @downcase_word = word.downcase
  end

  def match(potential_words)
    potential_words.select { |potential_word| anagram?(potential_word) }
  end

  def anagram?(potential_word)
    downcase_potential_word = potential_word.downcase

    downcase_potential_word != downcase_word && downcase_potential_word.each_char.sort == sorted_word
  end

  def sorted_word
    @sorted_word ||= downcase_word.each_char.sort
  end
end
