class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
  	words
  end

  private

  def words
  	if !@count
  	  get_words
  	  count_words
  	end
  	@count
  end

  def get_words
    @phrase.downcase!
    @phrase.gsub!(/\W/, ' ')
    @words = @phrase.split(' ')
  end

  def count_words
    @count = Hash.new(0)
    @words.each do |word|
      @count[word] += 1
    end
  end

end
