class Phrase
  attr_accessor :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    count = Hash.new(0)
    words = cutted_phrase
    words.each do |word|
      count[word]+=1
  	end
  	count
  end

  private
  def cutted_phrase
    @phrase.downcase.scan(/[\w]+/)
  end
end
