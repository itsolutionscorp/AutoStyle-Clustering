class Phrase
  def initialize(phrase)
    @phrase = clean_phrase(phrase)
  end

  def word_count
    @phrase.split(" ")
           .inject(Hash.new(0)) { |h, e| h[e] += 1 ; h }
  end

  private
  def clean_phrase(phrase)
    phrase.downcase.gsub(/[^ '0-9A-Za-z\n]/, " ")
  end
end
