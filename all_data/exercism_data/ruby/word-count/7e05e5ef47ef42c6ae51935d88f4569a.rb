class Phrase
  WORD_PATTERN = /\w+/

  attr_reader :content

  def initialize(content)
    @content = content
  end

  def word_count
    results = Hash.new(0)
    content.downcase.scan(WORD_PATTERN).each do |word|
      results[word] += 1
    end
    results
  end

end
