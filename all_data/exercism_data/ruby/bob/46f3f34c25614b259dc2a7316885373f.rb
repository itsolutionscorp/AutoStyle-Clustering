class Phrase

  attr_reader  :words

  def initialize(words)
    @words = words.downcase.gsub(/[^a-z\d\s]/, '').split
  end


  def word_count
      words.each_with_object(Hash.new(0)) { |o, h| h[o] += 1 }
  end
end
