class SecretHandshake
  Signs = ["wink", "double blink", "close your eyes", "jump"]
  def initialize (n)
    @n = n.to_i
  end
  def commands
    s = Signs.each_with_index.select{|s, i| (@n / 2**i) % 2 > 0}.map(&:first)
    (@n / 2**4) % 2 > 0 ? s.reverse : s
  end
end
