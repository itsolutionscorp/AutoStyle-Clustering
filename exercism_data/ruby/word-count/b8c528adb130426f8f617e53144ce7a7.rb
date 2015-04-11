class Phrase

  attr_reader :content

  def initialize(content)
    @content = content
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, counter|
      counter[word] += 1
    end
  end

  def words
    content.downcase.scan(/\w+/)
  end

end
