class Phrase
  def initialize(words)
    @words = words.downcase.gsub(/[,]/i, " ").gsub(/[^a-z0-9'\s]/i, '')
    @words = @words.split(" ")
  end
  def word_count
    result = Hash.new(0)
    @words.each {|x| result[x] += 1}
    result
  end
end
