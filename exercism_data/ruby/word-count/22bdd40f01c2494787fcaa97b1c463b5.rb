class Phrase
  def initialize(value)
    @string_value = value.to_s
  end

  def word_count
    words = Hash.new(0)
    normalized_words =  tokenize(normalize_string())
    normalized_words.each_with_object(words) do |word, word_hash|
      word_hash[word] += 1
    end

    words
  end

  private
  def normalize_string
    @string_value.downcase
  end

  def tokenize(string)
    string.scan(/[\w']+/)
  end
end
