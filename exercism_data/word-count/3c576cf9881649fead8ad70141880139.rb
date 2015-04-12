class Phrase
  def initialize(str)
    @topic = tokenizing(str)
  end

  def tokenizing(str)
    str.downcase.scan(/\w+/)
  end

  def word_count
    @topic.each_with_object(Hash.new(0)) { |word, result| result[word] += 1 } 
  end
end