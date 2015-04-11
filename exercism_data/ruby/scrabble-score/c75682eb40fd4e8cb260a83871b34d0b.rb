class Scrabble
  SCORE = {%w{A E I O U L N R S T }=> 1,
           %w{D G} => 2,
           %w{B C M P} => 3,
           %w{F H V W Y} => 4,
           ["K"] => 5,
           %w{J X} => 8,
           %{Q Z} => 10}

  def initialize(input)
    @word = ( input or "" ).upcase.scan(/\w/)
  end

  def score
    SCORE.each_pair.with_object Array.new(1,0) do |(key, value), result|
      @word.each do |char|
        result << key.count(char) * value
      end
    end.reduce(&:+)
  end

  private
  class << self
    def score(input)
      new(input).score
    end
  end
end
