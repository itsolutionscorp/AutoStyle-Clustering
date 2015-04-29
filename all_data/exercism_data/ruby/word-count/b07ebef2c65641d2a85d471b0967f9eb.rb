class Phrase
  WORD_PATTERN = /\w+/

  attr_reader :content

  def initialize(content)
    @content = content
  end

  def word_count
    results = Hash.new(0)
    words.each do |word|
      results[word] += 1
    end
    results
  end

  def words
    content.downcase.scan(WORD_PATTERN)
  end

end
