class Phrase

  attr_accessor :word_list

  def initialize(raw_sentence)
    @word_list = clean_invalid_characters(raw_sentence).split
  end

  def word_count
    word_counter = {}
    return word_counter unless word_list

    word_list.each do |raw_word|
      word = raw_word.downcase
      word_counter[word] = (word_counter[word] || 0) + 1
    end

    word_counter
  end

private

  def clean_invalid_characters(text)
    return '' if text.nil?
    text.gsub(/\W/,' ')
  end
end
