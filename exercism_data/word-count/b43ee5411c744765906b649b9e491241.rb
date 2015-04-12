class Phrase
  def initialize(phrase)
    @phrase = phrase.downcase
  end

  def word_count
    words_to_hash.tap do |hash|
      hash.each do |key, _|
        hash[key] = words.count key
      end
    end
  end

  private
    def words
      @words ||= @phrase.split(/\W+/)
    end

    def words_to_hash
      words.uniq.inject({}) do |memo, word|
        memo.merge word => nil
      end
    end
end
