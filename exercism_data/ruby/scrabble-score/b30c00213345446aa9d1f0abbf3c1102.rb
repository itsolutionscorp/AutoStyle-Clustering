class Scrabble
  def initialize(string)
    string ||= ''
    @chars = string.strip.upcase.chars
  end

  def score
    return 0 if @chars.empty?
    @chars.map{ |x| value x }.reduce(&:+)
  end

  def self.score(string)
    new(string).score
  end

  private
  def value(val)
    case val
    when 'A', 'E', 'I', 'O', 'U', 'L', 'N', 'R', 'S', 'T'
      1
    when 'D', 'G'
      2
    when 'B', 'C', 'M', 'P'
      3
    when 'F', 'H', 'V', 'W', 'Y'
      4
    when 'K'
      5
    when 'J', 'X'
      8
    when 'Q', 'Z'
      10
    end
  end
end
