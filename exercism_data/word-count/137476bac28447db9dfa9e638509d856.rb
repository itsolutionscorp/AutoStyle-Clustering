class Phrase
  
  def initialize(phrase)
    phrase ||= ''
    @phrase = phrase
  end
  
  def word_count
    counts = Hash.new(0)
    words.each do |word|
      counts[word.downcase] += 1
    end
    counts
  end
  
  private
  
    def words
      @phrase.split(/\W+/)
    end

end
