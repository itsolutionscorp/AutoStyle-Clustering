class Hamming
  def compute(str_one, str_two)
    diff = 0
    if str_one.length == str_two.length
      str_one.split('').each_with_index { |x, i| diff += 1 unless x == str_two[i] }
    else
      fail
    end

    diff
  end
end
