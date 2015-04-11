class SecretHandshake
  def initialize(n)
    @n = n
  end

  def commands
    return [] unless @n.is_a?(Integer)

    dict = { 0b1 => 'wink',
             0b10 => 'double blink',
             0b100 => 'close your eyes',
             0b1000 => 'jump' }

    res = dict.map { |key, val| val if @n & key == key }.compact
    (@n & 0b10000 == 0b10000) ? res.reverse : res
  end
end
