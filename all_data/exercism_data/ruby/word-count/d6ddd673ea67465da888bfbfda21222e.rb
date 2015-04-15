class Phrase

  attr_reader :message

  def initialize(message)
    @message = message
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, counter|
      counter[word] += 1
    end
  end

  private

  def words
    message.downcase.scan(/\w+/)
  end
end
