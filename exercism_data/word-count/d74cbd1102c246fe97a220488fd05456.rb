class Phrase
  attr_reader :content

  def initialize(content)
    @content = content
  end

  def word_count
    words.each_with_object(Hash.new 0) do |word, counts|
      counts[word] += 1
    end
  end

  private
  def words
    content.downcase.split(/\W+/)
  end
end
