class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    word_list.each_with_object(Hash.new(0)) do |word, hash|
      hash[word.downcase] += 1
    end
  end

  private

  def word_list
    @phrase.scan(/\w+/)
  end
end
