class Binary

  attr_reader :binchars
  def initialize(binary=0)
    @binchars = binary.chars
  end

  def to_decimal
    (0..binchars.size-1).inject(0) do |decimal, ndx|
      char = binchars.pop
      return 0 unless (['0','1'].include? char)
      decimal += char.to_i*(2**ndx)
    end
  end

end
