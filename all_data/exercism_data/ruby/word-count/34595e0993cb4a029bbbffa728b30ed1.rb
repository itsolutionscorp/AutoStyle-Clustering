class Phrase
  def initialize(phrase)
    @raw_phrase = phrase
  end

  def word_count
    words.each_with_object(counter) { |word, count| count[word] += 1 }
  end

  def words
    phrase.split(/[\s,]/).reject(&:empty?)
  end

  def phrase
    @raw_phrase.downcase.gsub(/[^0-9a-z,\s]/, '').squeeze(' ')
  end

  private

  def counter
    Hash.new { |h,k| h[k] = 0 }
  end
end
