class Phrase
  def initialize(value)
    @words = Hash.new(0)
    string_value = value.to_s
    words = words_from_string(string_value)
    count_words(words)
  end

  def word_count
    @words
  end

  private
  def count_words(word_list)
    word_list.each do |word|
      @words[word] += 1
    end
  end

  def words_from_string(value)
    value.downcase.scan(/[\w']+/)
  end
end
