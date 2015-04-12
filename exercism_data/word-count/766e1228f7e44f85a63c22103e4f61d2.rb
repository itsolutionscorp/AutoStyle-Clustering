class Phrase
  def initialize phrase
    @phrase = phrase
  end

  def word_count
    result_hash = {}
    split_phrase = @phrase.downcase.scan(/[\w']+/)
    split_phrase.each do |word|
      result_hash[word] = split_phrase.count(word)
    end
    result_hash
  end
end
