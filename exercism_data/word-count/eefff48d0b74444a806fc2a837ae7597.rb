class Phrase
  attr_reader :word_count
  def initialize(string)
    @word_count = Hash.new 0
    string.downcase.gsub(/[^\w\s,']/, '').split(/[,\s]+/).each { |w| @word_count[w] += 1 }
  end
end
