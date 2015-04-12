class Phrase
  def initialize(message)
    @message = message
  end

  def word_count
    count = message_array.inject({}) do |collection, word|
      collection[word] ||= 0
      collection[word] += 1
      collection
    end
    count
  end

  private

  def message_array
    @message.downcase.scan(/\b[a-z0-9]+\b/i)
  end
end
