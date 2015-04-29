# phrase.rb

class Phrase

  def initialize(string)
    @string = string
  end

  def word_count
    @string
      .scan(/[\w']+/)
      .each_with_object(Hash.new(0)) do |word, hash|
        hash[word.downcase] += 1
      end
  end

end
