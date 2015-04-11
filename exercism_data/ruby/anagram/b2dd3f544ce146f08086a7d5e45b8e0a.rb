class Anagram
  attr_reader :ordered, :normalized

  def initialize(word)
    @normalized = word.downcase()
    @ordered = order_word @normalized
  end

  def match(words)
    words.select do |w|
      w != normalized && order_word(w) == ordered
    end
  end

  private

  def order_word(word)
    word.split("").sort.join("")
  end
end
