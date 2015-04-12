class Phrase
  attr_accessor :phrase
  def initialize phrase
    @phrase = phrase
  end
  def word_count
    hash = Hash.new(0)
    words = @phrase.downcase.gsub(/[^\w\s',]/,'').split(/ |,/).select{|i| i.length > 0}
    words.each do |i|
      hash[i] += 1
    end
    hash
  end
end
