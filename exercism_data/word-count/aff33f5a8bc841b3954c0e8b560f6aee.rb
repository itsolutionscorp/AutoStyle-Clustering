class Phrase
  def initialize str
    @phrase = str
  end

  def word_count
    count = {}
    @phrase.split(/\W+/).map do |word|
      word = word.downcase
      if count.key? word
        count[word] = count[word] + 1
      else
        count[word] = 1
      end
    end
    count
  end
end
