class Phrase
  def initialize string
    @phrase = string
  end

  def words
    @phrase.downcase.scan(/[\w']+/)
  end

  def word_count
    word_count = Hash.new(0)
    words.each do |word|
      word_count[word] += 1
    end
    word_count
  end
end
