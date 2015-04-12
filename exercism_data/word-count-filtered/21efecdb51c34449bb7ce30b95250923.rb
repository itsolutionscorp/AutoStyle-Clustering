class Phrase
  attr_reader :words

  def initialize(words)
    @words = words
  end

  def word_count
    sentance = words.downcase.scan(/(\w+\'\w|\w+)/).flatten
    count    = sentance.group_by { |word| word }
    count.each { |k, v| count[k] = v.count }
  end
end
