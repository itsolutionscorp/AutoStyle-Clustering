class Phrase < Struct.new(:phrase)
  PUNCTUATION = /[^[:alnum:][:blank:]]/

  def word_count
    words.frequency
  end

  private
  def words
    normalized.split(/\s+/)
  end

  def normalized
    phrase.gsub(PUNCTUATION, '').downcase
  end
end

module Enumerable
  def frequency
    inject(Hash.new(0)) {|result, el| result[el] += 1; result}
  end
end
