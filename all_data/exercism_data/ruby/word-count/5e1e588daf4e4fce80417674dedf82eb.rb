class Phrase

  attr_reader :word_count

  def initialize(sentence)
    @word_count = get_words(sentence).inject(Hash.new(0)) do |m,word|
      key = word.downcase
      m[key] = m[key]  + 1
      m
    end
  end

  private

  def get_words(s)
    s.scan(/\w+(?:'\w+)?/)
  end

end
