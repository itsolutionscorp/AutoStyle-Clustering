class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    words.each_with_object(Occurrences.new) { |word, occurrences| occurrences << word }
  end

  def words
    normalize(@phrase).scan(/\w+(?:'\w)?/)
  end

  private

  def normalize(string)
    string.downcase
  end

  class Occurrences < ::Hash
    def initialize
      super 0
    end

    def <<(key)
      self[key] += 1
    end
  end
end
