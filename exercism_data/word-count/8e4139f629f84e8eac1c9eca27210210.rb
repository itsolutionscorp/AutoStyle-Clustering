class Phrase
  def initialize(text)
    @text = String(text).downcase
  end

  def word_count
    @word_count ||= count_words
  end

private
  def count_words
    words.each_with_object(Hash.new(0)) do |word, counter|
      increment_word_count(word, counter)
    end
  end

  def non_word_chars
    /[^\w]+/
  end

  def words
    @text.split(non_word_chars)
  end

  def increment_word_count(word, count)
    count[word] += 1
  end
end
