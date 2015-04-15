class Robot
  attr_accessor :name

  def initialize
    @name ||= get_name
  end

  def reset
    @name = get_name
  end

  private 

  def get_name
    chars = ('a'..'z').to_a + ('A'..'Z').to_a
    nums = ('0'..'9').to_a
    (chars.sample(2) + nums.sample(3)).join
  end
end
