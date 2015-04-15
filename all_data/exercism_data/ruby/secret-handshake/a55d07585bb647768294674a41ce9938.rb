class SecretHandshake
  HANDSHAKES = ['wink', 'double blink', 'close your eyes', 'jump']
               .each_with_index.to_h

  attr_reader :commands

  def initialize(n)
    n = String(n).to_i(2) unless n.respond_to?(:&)
    @commands = HANDSHAKES.select { |_, i| (2**i & n).nonzero? }.keys
    @commands.reverse! if (2**HANDSHAKES.count & n).nonzero?
  end
end
