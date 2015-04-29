class Phrase

  attr_accessor :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def word_count
    count_tokens
  end

  private

  def count_tokens
    #group_string.each { |token| { token => token.count } }
    tokenize_string.inject(Hash.new(0)) do |c,w|
      c[w] += 1
      c
    end
  end

  def tokenize_string
    phrase.gsub(/[^A-Za-z0-9\s]/, ' ').split(/\s|,/).reject { |s| s.empty? }.map &:downcase
  end

end
