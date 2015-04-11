class Phrase

  def initialize(string)
    @string = string
  end

  def word_count
    # Use default argument to Hash constructor to avoid excess logic in #reduce
    split.reduce(Hash.new(0)) do |word_list, word|
      word_list[normalize(word)] += 1
      word_list
    end
  end

  private

  def normalize(word)
    word.downcase.gsub(/[^[:word:]]/, '')
  end

  def split
    @string.split(/\W+/)
  end
end
