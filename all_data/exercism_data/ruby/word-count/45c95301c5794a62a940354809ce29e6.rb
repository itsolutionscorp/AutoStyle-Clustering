class Phrase < String

  def word_count
    Histogram.new(words)
  end

  def words
    scan(/\w+/).map { |w| w.downcase }
  end

  class Histogram < Hash
    def initialize(words)
      super 0
      words.each do |word|
        self[word] += 1
      end
    end
  end

end
