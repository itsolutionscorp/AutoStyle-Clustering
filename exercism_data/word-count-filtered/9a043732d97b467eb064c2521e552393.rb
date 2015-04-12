class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    phrase.downcase.gsub(/[^a-z'0-9]/, ' ').split(' ').inject(Hash.new(0)) { |hash, index| hash[index] += 1; hash }
  end

end
