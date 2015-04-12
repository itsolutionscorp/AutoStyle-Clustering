class Phrase
  def initialize(phrase)
    @words = phrase.downcase.scan(/\w+'?\w*/)
  end

  def word_count
    @words.inject(Hash.new(0)) do |hash, value|
      hash[value] += 1
      hash
    end
  end
end
