class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @counts ||= count_words
  end

  private
  def words
    @phrase.downcase.scan /[[:word:]]+/
  end

  def count_words
    counts = Hash.new(0)
    words.each {|word| counts[word] += 1}
    counts
  end

end
