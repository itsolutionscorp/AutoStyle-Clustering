class Words
  attr_reader :list

  def initialize(input)
    @list = parse(input.downcase)
  end

  def parse(words)
    words.split(/\W/).reject { |n| n.empty? }
  end

  def count
    list.inject(Hash.new(0)) do |hash, word|
      hash[word] += 1
      hash
    end
  end
end
