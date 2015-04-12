class Phrase

  def initialize(text)
    @text = text
  end

  def word_count
    words = @text.split(/\W+/)
    word_counts = Hash.new(0)

    words.each do |word|
      word.downcase!
      word_counts[word] +=1
    end
    
    word_counts
  end

end
