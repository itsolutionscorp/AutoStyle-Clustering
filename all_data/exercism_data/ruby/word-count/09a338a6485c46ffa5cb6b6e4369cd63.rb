class Phrase
  def initialize(value)
    @words = Hash.new(0)
    value = value.to_s
    value = normalize_case(value)
    value = replace_commas_with_spaces(value)
    value = remove_punctuation(value)
    count_words(value.split)
  end

  def word_count
    @words
  end

  private
  def count_word(word)
    @words[word] = @words[word] + 1
  end

  def count_words(words)
    words.each do |word|
      count_word(word)
    end
  end

  def normalize_case(value)
    value.downcase
  end

  def remove_punctuation(value)
    value.gsub(/[^a-z0-9\s]/,'')
  end

  def replace_commas_with_spaces(value)
    value.gsub(/[,]/,' ')
  end
end
