class Phrase
  def initialize(message)
    @message = message
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, counts|
      counts[word] += 1
    end
  end

  private

  attr_reader :message

  def words
    message.downcase.scan(/[\w']+/)
  end

end
