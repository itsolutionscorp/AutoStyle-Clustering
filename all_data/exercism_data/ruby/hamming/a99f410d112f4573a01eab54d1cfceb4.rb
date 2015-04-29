class Hamming

  def self.it_up(a, b)
    hamming = 0
    i = 0
    a.each_char do |char|
      if char != b[i]
        hamming += 1
      end
      i += 1
    end

    hamming
  end

  def self.compute(str_one, str_two)
    if str_one == str_two
      return 0
    end

    if str_one.length < str_two.length
      return Hamming.it_up(str_one, str_two)
    else
      return Hamming.it_up(str_two, str_one)
    end
  end
end
