class Phrase
  def initialize(str)
    @topic = tokenizing(str)
  end

  def word_count
    @word_count ||= @topic.each_with_object(Hash.new(0)) { |word, result| result[word] += 1 }
  end

  private

  def tokenizing(str)
    str.to_s.downcase.scan(/\w+/)
  end
end
