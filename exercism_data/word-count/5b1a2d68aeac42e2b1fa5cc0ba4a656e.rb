class Phrase
  attr_reader :topic

  def initialize(str)
    @topic = sanitize(str)
  end

  def sanitize(str)
    str.downcase.scan(/\w+/)
  end

  def word_count
    @topic.inject(Hash.new(0)) do |result, word| 
      result[word] += 1
      result
    end 
  end
end
