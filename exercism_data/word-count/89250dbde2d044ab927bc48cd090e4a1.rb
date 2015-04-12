class Phrase

  def initialize(phrase)
    @phrase = phrase
    #@counts = count_words
  end

  def word_count
    @counts ||= count_words
  end

  private
  def words
    @phrase.downcase.scan /[[:word:]]+/
  end

  def count_words
    #words  = get_words
    counts = Hash.new(0)
    words.each {|word| counts[word] += 1}
    counts
  end

end
