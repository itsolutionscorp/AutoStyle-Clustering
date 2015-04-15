class Rank
  attr_reader :word, :weight

  def initialize(word)
    @word   = word
    @weight = generate_weight
  end

  def ==(other_rank)
    word.downcase != other_rank.word.downcase &&
    weight        == other_rank.weight
  end

  private
  def generate_weight
    result = Hash.new(0)
    word.downcase.chars.each do |letter|
      result[letter] += 1
    end
    result
  end
end

class Anagram
  attr_reader :word, :rank

  def initialize(word)
    @word = word
    @rank = Rank.new(word)
  end

  def match(words)
    ranks = words.map {|word| Rank.new(word) }
    ranks.map {|r| r.word if rank == r }.compact
  end
end
