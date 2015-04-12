class Phrase
  attr_reader :phrase
  def initialize phrase
    @phrase = phrase
  end

  def word_count
    @phrase.scan(/[a-zA-Z0-9_\'\"]+/).inject(Hash.new {|hash, key| hash[key.downcase] = 0}) {|hash, word| hash[word.downcase] += 1; hash}
  end
end
