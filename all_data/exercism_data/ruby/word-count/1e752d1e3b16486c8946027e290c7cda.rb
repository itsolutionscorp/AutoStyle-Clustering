class Phrase
  def initialize(words)
    @word_list = _strip_bad_characters_from(words)
  end

  def word_count
    WordCounter.new(@word_list).count_words
  end

  private

  def _strip_bad_characters_from(words)
    words.gsub(/[:!@$%^&,.]/, ' ')
  end
end

class WordCounter
  def initialize(word_list)
    @word_array = _word_array_from_list(word_list)
  end

  def count_words
    @word_array.inject({}) do |result, word|
      result[word] = (result[word] || 0) + 1
      result
    end
  end

  private

  def _word_array_from_list(word_list)
    word_list.split(' ').map do |word|
      word.downcase
    end
  end
end
