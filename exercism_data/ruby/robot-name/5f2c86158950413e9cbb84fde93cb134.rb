class Robot
  
  attr_accessor :name
  PRNG = Random.new

  def initialize
    @name = ""
    2.times { @name << PRNG.rand('A'.ord..'Z'.ord).chr }
    3.times { @name << PRNG.rand(0..9).to_s }
  end

  def reset
    initialize
  end
end
