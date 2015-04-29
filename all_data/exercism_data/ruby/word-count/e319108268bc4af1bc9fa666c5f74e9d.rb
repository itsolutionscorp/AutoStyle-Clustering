class Phrase

  def initialize(phrase)
    @phrase = phrase.downcase
  end

  def word_count
    histogram = Hash.new(0)
    words.each do |word|
      histogram[word] += 1
    end
    return histogram
  end

  def words
    @phrase.scan(/\w+/)
  end

end
