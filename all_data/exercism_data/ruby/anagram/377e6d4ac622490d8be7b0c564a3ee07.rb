class Anagram
  def initialize(source_word)
    @source_word = source_word
  end

  def match(words)
    words.select { |candidate| anagram?(candidate) }
  end

  private

  # an anagram has the same letters as the source word and is not the source word
  def anagram?(candidate)
    same_letters?(candidate) && different_word?(candidate)
  end

  def different_word?(candidate)
    candidate.downcase != @source_word.downcase
  end

  def same_letters?(candidate)
    letters_in_word(candidate) == letters_in_word(@source_word)
  end

  def letters_in_word(word)
    word.downcase.chars.sort
  end
end
