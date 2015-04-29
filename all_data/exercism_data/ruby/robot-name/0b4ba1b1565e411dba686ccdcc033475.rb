class Robot

  def initialize
    @name = ''
  end

  def name
    letters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'
    numbers = '1234567890'
     2.times {|x| @name << letters[rand(26)]}
     3.times {|x| @name << numbers[rand(10)]}
     @name
  end

  def reset
    @name = ''
  end

end
