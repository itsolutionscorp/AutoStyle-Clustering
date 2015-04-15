class Robot
  attr_reader :name

  CHARS = 'A'..'Z'
  NUMS  = '0'..'1'

  def initialize
    reset
  end

  def reset
    @name = gen_name
  end

  private
  def gen_name
    CHARS.to_a.sample + CHARS.to_a.sample + NUMS.to_a.sample + NUMS.to_a.sample + NUMS.to_a.sample
  end
end
