class Phrase
  def initialize(text)
    text.downcase!
    
    @text = text

    if @text.match /\s/
      @text.gsub!(/[^\w\s']/, '')
      @words = @text.split /\s+/
    else
      @words = @text.split /,/
    end
  end
  
  def word_count
    result = {}
    @words.each do |word|
      result[word] = 0 unless result[word]
      result[word] += 1
    end
    
    result
  end
end
