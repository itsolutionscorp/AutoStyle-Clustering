class SecretHandshake
  attr_reader :commands

  ACTIONS = ['wink', 'double blink', 'close your eyes', 'jump']
  
  def initialize n
    n = 0 unless n.is_a?(Integer) && n >= 0 && n < 32
    @commands = ACTIONS.zip([1,2,4,8]).map { |a, i| a if n & i != 0 }.compact
    @commands.reverse! unless n & 16 == 0
  end
end
