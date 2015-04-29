class Phrase
  attr_reader :content

  def initialize(content)
    @content = content.downcase
  end

  def word_count
    words.each_with_object(Hash.new(0)) {|word, histogram| histogram[word] += 1 }
  end

  private

  def words
    @words ||= content.scan(/\w+/)
  end
end
