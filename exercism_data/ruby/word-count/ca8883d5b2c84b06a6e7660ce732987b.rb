class Phrase
  
  def initialize(phrase)
    @phrase = phrase
    @count = Hash.new
  end 

  def word_count
    word_list.each do |word|
      count = word_list.count(word)
      @count[word] = count
    end
    return @count
  end

  protected

  def word_list
    word_list = @phrase.downcase.delete('^a-z ','^0-9','^,',"^'").split(/[\s,]+/) 
  end


end
