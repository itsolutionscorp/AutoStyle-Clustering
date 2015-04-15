class Phrase
  attr_reader :content
  def initialize(content)
    @content = content
  end

  def words
    @words ||= content.scan(/[\w|\d]+/).map{|word| word.downcase }
  end

  # how can I get rid of this word_map local var?
  def word_count
    word_map = {}
    words.each {|word| word_map[word] = words.count(word) }
    word_map
  end

end
