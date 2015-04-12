class Phrase
  def initialize(string)
    @string = string
  end
  def word_count
    words = @string.downcase.gsub(/[^a-zA-Z0-9']/,' ').split(" ")
    frequencies = Hash.new(0)
    words.each {|word| frequencies[word] += 1 }

    frequencies
  end
end
