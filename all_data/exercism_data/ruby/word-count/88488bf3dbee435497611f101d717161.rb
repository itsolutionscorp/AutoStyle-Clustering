class Phrase
  def initialize(str)
    @topic = str
  end

  def word_count
    @word_count ||= tokenize.each_with_object(Hash.new(0)) { |word, result| result[word] += 1 }
  end

  private

  def tokenize
    @topic.to_s.downcase.scan(/\w+/)
  end
end
