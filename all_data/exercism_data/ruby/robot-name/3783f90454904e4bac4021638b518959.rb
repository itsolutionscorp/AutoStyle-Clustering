class Robot
  attr_accessor :name, :randy

  def initialize
    @randy = Random.new
    @name = make_name
  end

  def make_name
    rv = ""
    2.times { rv << randy.rand(65..90).chr }
    3.times { rv << randy.rand(0..9).to_s }
    rv
  end

  def reset
    self.name = make_name
  end
end
