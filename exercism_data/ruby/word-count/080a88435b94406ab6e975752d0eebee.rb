class Phrase
  attr_accessor :words
  def initialize(word)
    @words = clean(word)
  end
  def clean(word)
    word.downcase.gsub(/[^0-9a-z ]/i, '').split(" ")
  end
  def word_count
    counts = Hash.new
    words.each do |word|
      counts[word] ? counts[word] +=1 : counts[word] = 1
    end
    counts
  end
end
