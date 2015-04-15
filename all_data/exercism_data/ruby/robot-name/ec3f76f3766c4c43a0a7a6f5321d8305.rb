class Robot
  
  attr_accessor :name
  PRNG = Random.new

  def initialize
    create_name
  end

  def reset
    create_name
  end

  def create_name
    @name = ""
    2.times { @name << PRNG.rand('A'.ord..'Z'.ord).chr }
    3.times { @name << PRNG.rand(0..9).to_s }
  end
end
