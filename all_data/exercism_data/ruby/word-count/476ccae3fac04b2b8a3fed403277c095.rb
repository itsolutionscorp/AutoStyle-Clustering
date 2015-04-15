class Phrase
  attr_reader :phrase
  def initialize(phrase)
    @phrase = normalize(phrase)
  end

  def word_count
    phrase
      .split
      .inject(Hash.new(0)) {|h, word| h[word] += 1; h }
  end

  private

  def normalize(phrase)
    phrase
      .downcase
      .tr(',', ' ')
      .chars.select {|c| /[a-zA-Z0-9 ']/ =~ c}
      .join
  end

end
