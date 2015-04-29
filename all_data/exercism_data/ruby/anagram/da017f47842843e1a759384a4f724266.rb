class Anagram
  attr_reader :ordered, :word

  def initialize(word)
    @word = word.downcase()
    @ordered = order_word @word
  end

  def match(words)
    words.select{|w| w != word && order_word(w) == ordered}
  end

  private

  def order_word(word)
    word.split("").sort.join("")
  end
end
