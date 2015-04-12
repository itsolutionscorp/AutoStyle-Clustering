class Phrase
  def initialize(message)
    @message = message
  end

  def word_count
    words.each_with_object(Hash.new(0)) { |word, hash| hash[word] += 1 }
  end

  private

  def words
    @message.downcase.scan(/\w+/)
  end
end
