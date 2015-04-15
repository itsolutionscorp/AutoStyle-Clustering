class Binary
  def initialize num
    /[^01]/ === num ? @num = [0] : @num = num.chars
  end

  def to_decimal
    (0..@num.length).reduce(0){|acc, pow| acc + @num.pop.to_i*2**pow}
  end
end
