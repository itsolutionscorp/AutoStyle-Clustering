class SecretHandshake
  ACTIONS = ['wink', 'double blink', 'close your eyes', 'jump']

  def initialize num
    @handshake = num.to_i.to_s(2).chars.reverse
  end

  def commands
    cmd = []
    @handshake.each_with_index do |c, i|
      if i == 4
        cmd.reverse!
      else
        cmd << ACTIONS[i % 4] if c == '1'
      end
    end
    cmd
  end

end
