class Hexadecimal
  TRANS = {'0' => 0, 'a' => 10, 'b' => 11, 'c' => 12, 'd' => 13, 'e' => 14, 'f' => 15}

  def initialize(num)
    @num = num
  end

  def to_decimal
    @num.chars.each.with_index.inject(0) {|result, (n, i)|
      n.to_i > 0 ? n = n.to_i : n = TRANS[n]
      return 0 if n == nil
      result += n * 16 ** (@num.length - 1 - i)
    }
  end
end
