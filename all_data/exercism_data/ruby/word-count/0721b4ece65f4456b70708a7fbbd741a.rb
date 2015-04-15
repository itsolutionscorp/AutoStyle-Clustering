class Phrase
  def initialize(words)
    @words = words.downcase.gsub(/[,]/i, " ").gsub(/[^a-z0-9'\s]/i, '')
    @words = @words.split(" ")
  end
  def word_count
    @words.each.with_object(Hash.new(0)) {|x, result| result[x] += 1}
  end
end
