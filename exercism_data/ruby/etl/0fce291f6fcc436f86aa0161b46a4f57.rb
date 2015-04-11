class ETL
  attr_reader :old

  def initialize(old)
    @old = old
  end

  class << self
    def transform(old)
      ETL.new(old).transform
    end
  end

  def transform
    hash = Hash.new { |hash, key| hash[key] = 0 }
    each_score do |score, letter|
      hash[letter] += score
    end
    hash
  end

  private
  def each_score
    old.each_pair do |score, letters|
      letters.each do |letter|
        yield score, letter.downcase
      end
    end
  end
end
