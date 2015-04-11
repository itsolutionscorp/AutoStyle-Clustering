class Phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    cleaned_list.each_with_object(Hash.new(0)) do |word, hash|
      hash[word] += 1
    end
  end

  private

  def cleaned_list
    @phrase.downcase.scan /\w+/
  end
end
