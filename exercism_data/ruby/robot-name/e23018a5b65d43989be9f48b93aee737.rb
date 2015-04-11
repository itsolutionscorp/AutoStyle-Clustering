def rand_char
  rand('A'.ord..'Z'.ord).chr
end
class Robot
  attr_accessor :name

  def initialize
    reset
  end

  def reset
    @name = ""
    2.times { @name << rand_char }
    3.times { @name << rand(0..10).to_s }
  end
end
