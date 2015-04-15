class Phrase
  attr_reader :string

  def initialize(string)
    @string = string
  end

  def word_count
    words.each_with_object(Hash.new(0)) do |word, histogram|
      histogram[word.downcase] += 1
    end
  end

  def words
    string.split(/\W+/)
  end
end
