class Words
  attr_reader :words

  def initialize(words)
    @words = words
  end

  def parse_words
    list = words.split(/\W/).reject { |n| n.empty? }
    list.each {|word| word.downcase!}
  end

  def count
    list = parse_words
    list.inject(Hash.new(0)) do |hash, word|
      hash[word] += 1
      hash
    end
  end
end
