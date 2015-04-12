class Phrase
  attr_reader :text

  def initialize(phrase)
    @text = phrase
  end

  def word_count
    grouped_by_word.update(grouped_by_word) { |k,v| v.length }
  end

  private

  def sanitized_text
    @sanitized_text ||= text.gsub(/[^\w\s']/, ' ').downcase
  end

  def grouped_by_word
    sanitized_text.split(" ").group_by{|w|w}
  end
end
