class Phrase
  def initialize(text)
    @text = text
  end

  def word_count
    words = @text.scan /\w+/
    words.map(&:downcase).reduce({}) do |result, word|
      current_count = result.fetch(word, 0)
      result.update word => current_count.next
    end
  end
end
