class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words_in_phrase.each_with_object(Hash.new(0)) do |word,count|
      count[word] += 1
    end
  end

  private

  def words_in_phrase
    sanatized_phrase.scan(/\w+/)
  end

  def sanatized_phrase
    lowercased_phrase.gsub(/[^\w\s]+/, ' ')
  end

  def lowercased_phrase
    @phrase.downcase
  end
end
