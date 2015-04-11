class Phrase
  def initialize(string)
    @string = string.scan(/\w+/)
  end

  def word_count
    @string.each_with_object(Hash.new(0)) do |word, count|
      count[word.downcase] += 1
    end
  end
end
