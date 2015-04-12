class Phrase

  def initialize sentence
    @sentence = sentence
  end

  def word_count
    sentence
      .scan(/[\w']+/)
      .inject(Hash.new(0)) { |hash, word| hash[word.downcase] += 1; hash }
  end

  private

  attr_reader :sentence

end
