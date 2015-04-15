class Phrase
  def initialize(phrase)
    @phrase = phrase.downcase.gsub(/[^0-9a-z\s,']/, '')
    @words = @phrase.split(/,\s*|,|\s+/)
  end

  def word_count
    @words.inject(Hash.new(0)) do |hash, value|
      hash[value] += 1
      hash
    end
  end
end
