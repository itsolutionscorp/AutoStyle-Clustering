class Robot

  def initialize
    @name = ''
  end

  def name
    numbers = '1234567890'
    letters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'
    2.times {|i| @name << letters[rand(26)]}
    3.times {|i| @name << numbers[rand(10)]}
    @name
  end

  def reset
    @name = ''
  end

end
