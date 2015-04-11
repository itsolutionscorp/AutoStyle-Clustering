class Phrase

  def initialize(phrase)
    @phrase = split(phrase)
  end

  def word_count
    count_of_words = {}
    @phrase.each do |words|
      count_of_words[words] = @phrase.count(words)
    end
    count_of_words
  end

  def split(string)
    string.downcase.scan(/[\w']+/)
  end

end
