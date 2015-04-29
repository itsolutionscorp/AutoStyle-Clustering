class Phrase
  def initialize phrase
    @phrase = phrase
  end

  def word_count
    arr = @phrase.downcase.gsub(/[^a-z0-9]/, " ").split(" ")
    arr.each_with_object(Hash.new(0)) { |e, h| h[e] += 1; h }
  end
end
