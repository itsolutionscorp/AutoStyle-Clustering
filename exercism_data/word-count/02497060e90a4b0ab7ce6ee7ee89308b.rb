class Phrase
  attr_reader :message

  def initialize(message)
    @message = message
  end

  def word_count
    counts = Hash.new(0)
    words.each_with_object(counts) {|word| counts[word] += 1 }
  end

  private

  def words
    message.downcase.scan(/\w+/)
  end

end
