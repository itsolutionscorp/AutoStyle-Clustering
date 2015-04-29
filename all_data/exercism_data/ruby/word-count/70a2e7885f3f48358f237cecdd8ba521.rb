class Phrase
  attr_accessor :phrase

  def initialize(input)
    @phrase = input
  end

  def word_count
    hash         = {}
    hash.default = 0

    @phrase.gsub(",", ", ").split.map do |word|
      word = word.match(/[[:word:]]*/).to_s.downcase
      next if word == ""
      hash[word] += 1
    end

    hash
  end
end
