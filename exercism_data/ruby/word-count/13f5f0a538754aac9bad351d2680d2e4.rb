class Phrase

  def initialize(phrase)
    @clean_words_array = sanitize_text phrase
  end

  def word_count
    count_each_word @clean_words_array
  end

  private

  def sanitize_text(text)
    text.downcase.scan(/\w+/)
  end

  def count_each_word(text)
    text.each_with_object(Hash.new(0)) do |word, counts|
      counts[word] += 1
    end
  end

end
