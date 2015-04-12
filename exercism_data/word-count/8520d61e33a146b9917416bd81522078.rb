class Phrase
  def initialize phrase
    @phrase = phrase.to_s
  end

  def word_count
    hash = Hash.new 0

    p = replace_punctuation_with_space(@phrase.downcase)
    p.split.each { |word| hash[word] += 1 }

    hash
  end

  private

  def replace_punctuation_with_space w
    #\W not a word character
    w.gsub(/\W/, ' ')
  end
end
