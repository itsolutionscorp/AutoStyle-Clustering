class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    word_list.each_with_object(Hash.new(0)) do |word, stash|
      stash[normalize(word)] += 1
    end
  end

  private

  def word_list
    @phrase.scan(/\w+/)
  end

  def normalize(word)
    word.downcase
  end
end
