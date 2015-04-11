class Phrase
  def initialize(phrase)
    @phrase=String(phrase)
  end
  def word_count
    word_counts = {}
    word_counts.default=0
    @phrase.downcase!
    @phrase.gsub!(/[^a-z0-9 ,]/,'')
    words=@phrase.split(/[\s,]+/)
    words.each do |word|
      word_counts[word] = word_counts[word] + 1
    end    
    word_counts
  end
end
