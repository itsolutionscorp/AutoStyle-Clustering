class Phrase
  def initialize(phrase)
    @phrase = phrase || ""
    @count = Hash.new(0)
  end

  def word_count
    @count.clear
    @phrase.split(/[^\w']+/).each do |word|
      @count[word.downcase] += 1
    end
    @count
  end
end
