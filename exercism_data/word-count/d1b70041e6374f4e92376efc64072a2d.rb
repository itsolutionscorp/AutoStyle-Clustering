class Phrase

  def initialize(phrase)
    @phrase = phrase.dup
  end

  def word_count
  	if !@count
  	  progress_phrase
  	  count_words
  	end
  	@count
  end

  private

  def progress_phrase
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
