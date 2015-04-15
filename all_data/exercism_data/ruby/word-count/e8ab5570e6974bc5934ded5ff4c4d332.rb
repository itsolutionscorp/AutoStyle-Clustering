class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @result = Hash.new(0)
    @phrase.split(/[!@$%&,.;: ^]/)
           .select { |x| x != "" }
           .map    { |x| x.downcase }
           .each   { |x| count_word(x) }
    @result
  end

  def count_word(word)
    @result[word] = @result[word] + 1
  end
end
