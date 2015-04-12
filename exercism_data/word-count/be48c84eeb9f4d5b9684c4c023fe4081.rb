class Phrase

  def initialize(phrase)
    @words = split_words(phrase.downcase)
  end

  def word_count
    @words.each_with_object(Hash.new(0)) { |w,hsh| hsh[w] += 1 }
  end

  private

  def split_words(phrase)
    phrase.gsub(/\W+/, ' ').split(' ')
  end
end
