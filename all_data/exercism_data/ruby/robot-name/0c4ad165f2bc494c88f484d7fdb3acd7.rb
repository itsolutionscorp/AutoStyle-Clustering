class Robot
  def initialize
    @name = reset
  end
  def name
    @name
  end
  def reset
    @name = ""
    2.times do
      @name = @name + randomletter(rand(26))
    end
    3.times do
      @name = @name + rand(10).to_s
    end
    @name
  end
  def randomletter(num)
    letter = {0 => 'A', 1 => 'B', 2 => 'C', 3 => 'D', 4 => 'E',
              5 => 'F', 6 => 'G', 7 => 'H', 8 => 'I', 9 => 'J',
              10 => 'K', 11 => 'L', 12 => 'M', 13 => 'N', 14 => 'O',
              15 => 'P', 16 => 'Q', 17 => 'R', 18 => 'S', 19 => 'T',
              20 => 'U', 21 => 'V', 22 => 'W', 23 => 'X', 24 => 'Y',
              25 => 'Z'}
    out = letter[num]
  end
end
