class Phrase
  attr_reader :content

  def initialize(content)
    @content = content.downcase
  end

  def words
    @words ||= content.scan(/[\w|\d]+/)
  end

  def word_count
    words.each_with_object({}) {|word, word_map| word_map[word] = words.count(word) }
  end
end
