class Words

  attr_reader :words

  def initialize(words)
    @words = words.downcase.gsub(/[^0-9a-z ]/i, '')
  end

  def count
    words.split(" ").inject(Hash.new(0)) do |counts, word|
      counts[word] += 1
      counts
    end
  end


end
