class Phrase
  def initialize phrase
    @phrase = phrase.downcase.scan(/[\w']+/)
  end

  def word_count
    count = Hash.new(0)
    @phrase.each do |word|
      count[word] += 1
    end
    count
  end
end
