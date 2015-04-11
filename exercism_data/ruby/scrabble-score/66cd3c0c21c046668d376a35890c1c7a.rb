class Scrabble
  SCORE = {%w{A E I O U L N R S T }=> 1,
           %w{D G} => 2,
           %w{B C M P} => 3,
           %w{F H V W Y} => 4,
           ["K"] => 5,
           %w{J X} => 8,
           %{Q Z} => 10}

  def initialize(input)
    begin
    @word = input.upcase.scan(/\w/)
    rescue
      @word = []
    end
  end

  def score
    result = 0
    SCORE.each_pair do |key, value|
      @word.each do |char|
        result += key.count(char) * value
      end
    end
    result
  end

  class << self
    def score(input)
      new(input).score
    end
  end
end
