class Phrase
  def initialize(sentence)
    @sentence = sentence
  end

  def word_count
    words.each_with_object(Hash.new(0)) { |word, stats| stats[word] += 1 }
  end

  private

  def words
    lowerize.scan(wanted)
  end

  def lowerize
    @sentence.downcase
  end

  def wanted
    %r(\w+)
  end
end
