class ETL
  attr_reader :score_book, :new_score_book

  class << self
    def transform(score_book)
      ETL.new(score_book).transform
    end
  end

  def initialize(score_book)
    @score_book = score_book
    @new_score_book = Hash.new { |new_score_book, letter| new_score_book[letter] = 0 }
  end

  def transform
    each_score do |score, letter|
      new_score_book[letter] += score
    end
    new_score_book
  end

  private
  def each_score
    score_book.each_pair do |score, letters|
      letters.each do |letter|
        yield score, letter.downcase
      end
    end
  end
end
