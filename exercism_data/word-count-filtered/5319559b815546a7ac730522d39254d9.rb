class Phrase
  def initialize phrase
    @phrase = phrase
  end

  def word_count
    words = @phrase.gsub(/[,.!&@$%^&:]/, ' ').downcase.split(" ")
    counts = {}
    words.each do |word|
      counts[word] ||= 0
      counts[word] += 1
    end
    counts
  end
end
