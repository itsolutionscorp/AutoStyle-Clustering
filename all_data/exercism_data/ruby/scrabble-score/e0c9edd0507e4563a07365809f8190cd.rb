class Scrabble
  
  private
  def self.mount_scoreboard
    @scores = {}
    ('A'..'Z').each do |letter|
      @scores[letter.to_sym] = 1 if  %w[A E I O U L N R S T].include? letter
      @scores[letter.to_sym] = 2 if  %w[D G].include? letter
      @scores[letter.to_sym] = 3 if  %w[B C M P].include? letter
      @scores[letter.to_sym] = 4 if  %w[F H V W Y].include? letter
      @scores[letter.to_sym] = 5 if  %w[K].include? letter
      @scores[letter.to_sym] = 8 if  %w[J X].include? letter
      @scores[letter.to_sym] = 10 if  %w[Q Z].include? letter
    end
  end

  public
  def initialize(word)
    @word = word
  end

  def self.score(word)
    return 0 if word.nil?
    mount_scoreboard
    word.upcase.strip.split('').inject(0) {|score, n| score + @scores[n.to_sym]}
  end

  def score
    Scrabble.score(@word)
  end

end
