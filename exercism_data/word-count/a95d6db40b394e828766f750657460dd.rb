class Phrase
  def initialize(message)
    @message = message
  end

  def word_count
    message_array.each_with_object(Hash.new(0)) { |word, hash| hash[word] += 1 }
  end

  private

  def message_array
    @message.downcase.scan(/\w+/)
  end
end
