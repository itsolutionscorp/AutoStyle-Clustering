class SecretHandshake
  attr_reader :commands

  def initialize(n)
    if n.is_a?(Integer)
      digits = n.to_s(2).reverse
      ops = ["wink", "double blink", "close your eyes", "jump", nil]
      @commands = digits.chars.map.with_index { |d, i| d == "1" ? ops[i] : nil }.compact
      @commands.reverse! if digits.length == 5
    else
      @commands = []
    end
  end
end
