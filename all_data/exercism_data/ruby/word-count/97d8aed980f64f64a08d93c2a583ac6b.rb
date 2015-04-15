class Phrase
  attr_reader :content

  def initialize(content)
    @content = content.downcase
  end

  def words
    @words ||= content.scan(/\w+/)
  end

  def word_count
    words.each_with_object(Hash.new(0)) {|word, word_map| word_map[word] += 1 }
  end
end
