class Robot
  attr_reader :name

  def initialize
    @name = new_name
  end

  def new_name
    letters = ""
    2.times { letters << ('A'..'Z').to_a[rand(26)] }
    numbers = ""
    3.times { |x| numbers << (x + rand(10-x)).to_s }
    @name = "#{letters}#{numbers}"
  end

  def reset
    new_name
  end
end
