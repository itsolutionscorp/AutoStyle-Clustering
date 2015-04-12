# phrase.rb

class Phrase

  def initialize(string)
    @string = string
  end

  def word_count
    @string
      .scan(/[\w']+/)
      .inject(Hash.new(0)) do |hash, word|
        hash[word.downcase] += 1
        hash
      end
  end

end
