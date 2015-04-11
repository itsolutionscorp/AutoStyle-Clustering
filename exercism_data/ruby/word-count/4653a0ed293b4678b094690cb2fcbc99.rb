class Phrase
  def initialize(str)
    @phrase = str
  end

  def word_count
    @phrase.split(/\W+/).inject({}) do |count, word|
      word.downcase!
      count[word] ||= 0
      count[word] += 1
      count
    end
  end
end
