class Phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    @word_count ||= begin
      hash = Hash.new(0)
      @phrase.split(/\W+/).each do |word|
        hash[word.downcase] += 1
      end
      hash
    end
  end
end
