class Phrase
  attr_reader :words
  def initialize(phrase)
    @phrase = phrase.downcase
    @words = formater
  end

  def formater
    if @phrase.match(/[a-z]+[,][a-z]/)
      @phrase.gsub(/[^,a-z]/, "").split(",")
    else
      @phrase.gsub(/[^'a-z\d\s]/, "").split
    end
  end

  def word_count
    @words.inject(Hash.new(0)) {|hash,word| hash[word] += 1; hash }
  end
end
