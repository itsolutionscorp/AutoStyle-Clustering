class Phrase
  def initialize(word)
    @word = word
  end

  def word_count
    words.map(&:downcase).each_with_object(Hash.new(0)) do |w, grouped|
      grouped[w] += 1
    end
  end

  private

  def words
    @word.split(/[^'\w]+/)
  end
end
