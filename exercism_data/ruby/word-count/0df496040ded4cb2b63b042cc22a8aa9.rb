class Phrase
  def initialize phrase
    @phrase = phrase
  end

  def word_count
    word_count = {}
    @phrase.gsub(/[^0-9a-z,' ]/i, '').gsub(',',' ').downcase.split(' ').each do |word|
      if word_count.has_key?(word)
        word_count[word] += 1
      else
        word_count[word] = 1
      end
    end
    word_count
  end
end
