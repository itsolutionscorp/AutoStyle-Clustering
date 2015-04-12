class Phrase
  def initialize(phrase)
    @words = split_words(phrase)
  end

  def word_count
    @words.each_with_object(counting_hash) do |w, hash|
      hash[w] += 1
    end
  end

  def split_words(phrase)
    phrase.downcase.gsub(/[^a-z0-9,'\s]/, '').split(/[\s,]+/)
  end

  def counting_hash
    Hash[unique_words.map {|w| [w, 0]}]
  end

  def unique_words
    @words.uniq
  end
end
