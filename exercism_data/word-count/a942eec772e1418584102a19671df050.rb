class Phrase
  def initialize(value)
    @string_value = value.to_s
  end

  def word_count
    words = Hash.new(0)
    normalized_words = @string_value.downcase.scan(/[\w']+/)
    normalized_words.each_with_object(words) do |word, word_hash|
      word_hash[word] += 1
    end

    words
  end
end
