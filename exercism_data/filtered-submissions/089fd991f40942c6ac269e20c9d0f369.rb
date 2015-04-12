class Hamming

  def compute(str_one, str_two)

    if str_one.size == [str_one.size, str_two.size].min
      short = str_one
      long = str_two
    else
      short = str_two
      long = str_one
    end

    hamming = 0
    i = 0
    short.each_char { |char| hamming += 1 if char != long[i]; i += 1 }

    hamming
  end
end
