class Phrase
  def initialize(value)
    @string_value = value.to_s
  end

  def word_count
    normalized_words = tokenize(normalize_string())
    normalized_words.each_with_object(Hash.new(0)) do |word, word_hash|
      word_hash[word] += 1
    end
  end

  private
  def normalize_string
    @string_value.downcase
  end

  def tokenize(string)
    string.scan(/[\w']+/)
  end
end
