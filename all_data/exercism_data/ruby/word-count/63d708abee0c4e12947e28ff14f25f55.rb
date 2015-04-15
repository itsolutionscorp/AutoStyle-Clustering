class Phrase
  def initialize(str)
    @cache = {}
    @topic = tokenizing(str)
  end

  def tokenizing(str)
    str.to_s.downcase.scan(/\w+/)
  end

  def word_count
    @cache[@topic] ||= @topic.each_with_object(Hash.new(0)) { |word, result| result[word] += 1 }
  end
end
