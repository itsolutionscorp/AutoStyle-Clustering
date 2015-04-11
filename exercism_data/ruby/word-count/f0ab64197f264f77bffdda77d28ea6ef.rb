class Phrase

  def initialize(phrase)
    @phrase = phrase.downcase
  end

  def word_count
    #Hash[*array_of_words.uniq.collect {|w| [w,array_of_words.count(w)]}.flatten]
    Hash.new.tap do |h|
      words.collect do |word|
        h[word] = array_of_words.count(word)
      end
    end
  end

private

  def words
    array_of_words.uniq
  end

  def array_of_words
    @phrase.scan(/[\w\']+/)
  end

end
