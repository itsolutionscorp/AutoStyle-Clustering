class Phrase

  def initialize(phrase)
    @words = phrase.split(/\W+/)
  end

  def word_count
    Hash.new(0).tap do |hsh|
      @words.each do |word|
        hsh[word.downcase] += 1
      end
    end
  end

end
