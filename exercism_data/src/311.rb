class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    count = Hash.new(0)
    words = @phrase.downcase.gsub(/[^\w\s,]/, '').split(/[,\s]+/)
    words.each do |word|
      count[word] = count[word] + 1
    end
    return count
  end
end
