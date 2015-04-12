class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    uniq_words = []
    counts_hash = {}

    words = @phrase.split(/\W+/)
    words.each do |w|
      u_w = w.upcase
      if counts_hash.key?(u_w)
        counts_hash[u_w] += 1
      elsif
        counts_hash[u_w] = 1
        uniq_words << w
      end
    end

    result = {}
    uniq_words.each do |w|
      result[w] = counts_hash[w.upcase]
    end

    result
  end
end
