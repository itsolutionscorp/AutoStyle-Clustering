class SecretHandshake
  def initialize(num)
    num.class == Fixnum ? @command = num.to_s(2).rjust(5, "0") : @command = "00000"
  end

  def commands
    shake_moves = []
    shake_moves << "wink"            if @command[-1] == "1"
    shake_moves << "double blink"    if @command[-2] == "1"
    shake_moves << "close your eyes" if @command[-3] == "1"
    shake_moves << "jump"            if @command[-4] == "1"
    shake_moves.reverse!             if @command[-5] == "1"
    return shake_moves
  end
end
