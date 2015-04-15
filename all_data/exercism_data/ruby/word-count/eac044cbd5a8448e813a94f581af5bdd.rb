class Phrase

  def initialize phrase
    @phrase = phrase
  end

  def word_count
    normalize_phrase.split(' ').each_with_object(Hash.new(0)) { |i, a| a[i] += 1}
  end

  private

  def normalize_phrase
    @phrase.downcase.gsub(/[^a-z|0-9\s]/, '')
  end

end
