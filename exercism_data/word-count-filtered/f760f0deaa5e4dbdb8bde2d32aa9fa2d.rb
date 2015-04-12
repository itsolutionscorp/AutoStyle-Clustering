class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words = @phrase.split(/[^\w']/)

    w = {}
    words.each do |word|
      unless word.empty?
        word.downcase!
        count = w.fetch(word, 0)
        count += 1
        w[word] = count
      end
    end
    w
  end
end
