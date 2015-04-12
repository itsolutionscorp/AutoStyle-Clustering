class Phrase
  def initialize(string)
    @string = string
  end
  def word_count
    counts = Hash.new(0)
    @string.downcase.scan(/[\w']+/).each do |i|
      counts[i] += 1
    end
    return counts
  end
end
