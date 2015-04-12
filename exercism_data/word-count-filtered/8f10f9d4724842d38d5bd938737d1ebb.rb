class Phrase
  attr_reader :string

  def initialize(string)
    @string = string
  end

  def word_count
    h = Hash.new(0)
    string.downcase.scan(/[\w']+/).each do |word|
      h[word] += 1
    end
    h
  end
end
