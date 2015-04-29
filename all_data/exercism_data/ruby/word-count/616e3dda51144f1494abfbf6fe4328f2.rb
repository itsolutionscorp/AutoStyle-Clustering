class Phrase

  def initialize(text)
    @text = text
  end
  
  def word_count
    @word_count ||= calculate_word_count
  end

  private
  attr_reader :text
  
  def calculate_word_count
    words.each_with_object(Hash.new(0)) { |word, counter| counter[word] += 1 }
  end

  def words
    @words ||= text.downcase.scan /\w+/
  end
end
