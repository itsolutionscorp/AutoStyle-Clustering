class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = {}
    @phrase.split(/[, ]/).each do |word|
      word = word.gsub(/[^0-9a-zA-Z']+/, '').downcase
      if not word.empty?
        if words.has_key? word
          words[word] += 1
        else
          words[word] = 1
        end
      end
    end

    words
  end

end
