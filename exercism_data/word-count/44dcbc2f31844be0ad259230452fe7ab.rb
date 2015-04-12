class Phrase

  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase.downcase
  end

  def word_count
    count_words
  end

  private

  def count_words
    word_matcher = %r{\w+}
    scan_phrase_for(word_matcher).counting_each do |word, word_counts|
      word_counts.count(word)
    end
  end

  def scan_phrase_for(matcher)
    phrase.scan(matcher)
  end

end

class Array
  def counting_each
    each_with_object(Hash.new(0)) do |item, counting_hash|
      yield(item, counting_hash)
    end
  end
end

class Hash
  def count(item)
    self[item] += 1
  end
end
