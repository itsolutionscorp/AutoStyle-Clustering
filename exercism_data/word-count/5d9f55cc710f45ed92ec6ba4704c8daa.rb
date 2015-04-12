class Phrase
  def initialize(string)
    @string = string.downcase
  end

  def word_count
    @string.scan(/\w+/).inject({}) do |counter, word|
      counter[word] ||= 0
      counter[word] += 1
      counter
    end
  end

end
