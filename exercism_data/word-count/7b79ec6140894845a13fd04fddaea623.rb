class Phrase

  def initialize(string)
    @string = string.downcase
  end

  def word_count
    # Use default argument to Hash constructor to avoid excess logic in #reduce
    split.reduce(Hash.new(0)) do |word_list, word|
      word_list[word] += 1
      word_list
    end
  end

  private

  def split
    @string.scan(/\w+/)
  end
end
