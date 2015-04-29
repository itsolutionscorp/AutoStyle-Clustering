class Phrase
  attr_reader :content

  def initialize(content)
    @content = content
  end

  def words
    content.downcase.scan(/[\w']+/)
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, count|
      count[word] += 1
    end
  end

end
