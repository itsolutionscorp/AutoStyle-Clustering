class Scrabble
  def initialize(str)
    @word = str.to_s.strip.upcase

    @pt_1  = ['A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T']
    @pt_2  = ['D', 'G']
    @pt_3  = ['B', 'C', 'M', 'P']
    @pt_4  = ['F', 'H', 'V', 'W', 'Y']
    @pt_5  = ['K']
    @pt_8  = ['J', 'X']
    @pt_10 = ['Q', 'Z']

    @points = {
      1  => @pt_1,
      2  => @pt_2,
      3  => @pt_3,
      4  => @pt_4,
      5  => @pt_5,
      8  => @pt_8,
      10 => @pt_10 }
  end



  def score
    score = 0

    unless @word.empty?
      @word.each_char do |c|
        @points.each do |k, v|
          score += k if v.include? c
        end
      end
    end

    return score
  end

  class << self
    def score(word)
      Scrabble.new(word).score
    end
  end
end
