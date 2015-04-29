class Words
  attr_reader :list

  def initialize(list)
    @list = list.downcase
  end

  def parse_words
    list.split(/\W/).reject { |n| n.empty? }
  end

  def count
    words = parse_words
    words.inject(Hash.new(0)) do |hash, word|
      hash[word] += 1
      hash
    end
  end
end
