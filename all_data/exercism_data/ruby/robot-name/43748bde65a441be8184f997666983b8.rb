class Robot

  attr_accessor :name

  ASCII_A = ?A.getbyte(0)

  def initialize
    reset
  end

  def reset
    @name = ''
    2.times { @name << (ASCII_A + rand(26)).to_s }
    3.times { @name << rand(10).to_s }
  end

end
