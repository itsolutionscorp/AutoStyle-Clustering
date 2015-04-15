class SecretHandshake
  MOVES = ['wink', 'double blink', 'close your eyes', 'jump']
  attr_accessor :commands, :binary

  def initialize n
    @binary = to_binary n
    @commands = determine_moves binary
  end

  private

  def determine_moves b
    moves = b.chars
             .reverse_each
             .with_index
             .with_object([]) { |(c, i), a| a << MOVES[i] if c == '1' }
    (b.length == 5 ? moves.reverse : moves).compact
  end

  def to_binary n
    n.to_i.to_s 2
  end
end
