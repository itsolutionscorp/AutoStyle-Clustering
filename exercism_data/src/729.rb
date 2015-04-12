class Phrase
  attr_reader :original_phrase

  def initialize(string)
    @original_phrase = string
  end

  def word_count
    original_phrase.scan(/[\w']+/)
                   .each_with_object(Hash.new(0)) { |e, a| a[e.downcase] += 1 }
  end
end
