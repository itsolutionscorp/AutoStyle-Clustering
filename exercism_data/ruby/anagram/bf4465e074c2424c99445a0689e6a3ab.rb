class Anagram
  def initialize(word)
    @word = word
  end

  def match(candidates)
    candidates.select do |candidate|
      word_is_anagram_for?(candidate, @word)
    end
  end

  private
  def word_is_anagram_for?(candidate, word)
    candidate = candidate.downcase
    word = word.downcase

    unless candidate.length == word.length
      return false
    end

    if word == candidate
      return false
    end

    chars_in_same_frequenzy?(candidate, word)
  end

  def chars_in_same_frequenzy?(word, another_word)
    unique_chars_in_word = word.each_char.to_a.uniq

    unique_chars_in_word.all? do |char|
      regex = /#{char}/

      another_word.scan(regex).length == word.scan(regex).length
    end
  end
end
