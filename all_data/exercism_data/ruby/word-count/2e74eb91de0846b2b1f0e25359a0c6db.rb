class Phrase
  def initialize(value)
    @string_value = value.to_s
  end

  def word_count
    if @words.nil?
      @words = Hash.new(0)
      words = words_from_string(@string_value)
      count_words(words)
    end

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
