class Phrase
  attr_accessor :words

  def initialize phrase
    @words = phrase.downcase.gsub(/\W/, " ").split
  end

  def word_count
    counts = {}
    words.each{|word|
      counts[word] ||= 0
      counts[word] += 1
    }
    counts
  end
end
