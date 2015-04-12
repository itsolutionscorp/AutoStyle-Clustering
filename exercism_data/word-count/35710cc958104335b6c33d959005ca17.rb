class Phrase
  def initialize(text)
    @text = text
  end

  def word_count
    count_words_in word_array
  end

private
  def count_words_in(words)
    words.each_with_object(hash_with_zero_default) do |word, count|
      count[word.downcase] += 1
    end
  end

  def non_word_chars
    /[^\w]+/
  end

  def word_array
    @text.split(non_word_chars)
  end

  def hash_with_zero_default
    Hash.new(0)
  end
end
