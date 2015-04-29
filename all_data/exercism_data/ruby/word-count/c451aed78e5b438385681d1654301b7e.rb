class Phrase
  def initialize(value)
    @string_value = value.to_s
  end

  def word_count
    if @words.nil?
      initialize_words
    end

    @words
  end

  private
  def count_words(word_list)
    word_list.each_with_object(@words) do |word, word_hash|
      word_hash[word] += 1;
    end
  end

  def initialize_words
    @words = Hash.new(0)
    words = words_from_string(@string_value)
    count_words(words)
  end

  def words_from_string(value)
    value.downcase.scan(/[\w']+/)
  end
end
