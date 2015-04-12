class Phrase
  def initialize(text)
    @text = text
  end

  def word_count
    words = @text.scan /\w+/
    words.map(&:downcase).reduce({}) do |hash, key|
      current_count = hash.fetch(key, 0)
      hash.merge key => current_count.next
    end
  end
end
