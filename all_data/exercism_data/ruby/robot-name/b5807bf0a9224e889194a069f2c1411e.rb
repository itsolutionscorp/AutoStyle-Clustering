class Robot
  attr_reader :name

  def initialize
    reset
  end

  def reset
    @name = ""
    
    2.times { @name << (65 + rand(25)).chr }
    3.times { @name << rand(10).to_s }
  end

end
