class Phrase

  def initialize(sentence)
    @sentence = sentence
  end

  def word_count
    words.inject(Hash.new(0)) { |stats, word| stats[word] += 1; stats }
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
