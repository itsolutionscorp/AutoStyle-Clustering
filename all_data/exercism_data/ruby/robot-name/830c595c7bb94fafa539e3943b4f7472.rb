class Robot
  attr_accessor :name

  def initialize
    @name = generate_name
  end

  def reset
    while true do
      new_name = generate_name
      next if @name == new_name
      @name = generate_name
      break
    end
  end

  # The only way I can think of for tracking robot names to avoid collision,
  # would be ever-growing class variable with all the robot names (indexing
  # them might be a good idea). There's only 24 * 24 * 1000 = 576.000
  # combinations possible, so without annihilating poor robots collisions
  # are unavoidable. Then again, I'm kinda short on imagination...

  private

  def generate_name
    new_name = ('A'..'Z').to_a.sample(2).join
    3.times { |n| new_name += ('0'..'9').to_a.sample }
    new_name
  end
end
