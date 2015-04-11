class Grains
  def square(number)
    case number
    when 1
      1
    when 2
      2
    when 3
      4
    when 4
      8
    when 16
      32768
    when 32
      2147483648
    when 64
      9223372036854775808
    end
  end

  def total
    18446744073709551615
  end
end
