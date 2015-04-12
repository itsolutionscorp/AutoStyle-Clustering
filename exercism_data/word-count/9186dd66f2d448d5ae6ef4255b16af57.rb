#require 'regexp'

class Phrase
  def initialize phrase
    @phrase = phrase
  end
  
  def word_count
    word_counts = Hash.new(0)
    @phrase.downcase.gsub(/\W/, " ").split(" ").each { | word | word_counts[word] += 1 }
    word_counts
  end
end
