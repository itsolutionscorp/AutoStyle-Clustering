=begin
  File: robot.rb
  Author: sherinom 
=end

class Robot

  CHARS = [*'A'..'Z']
  NUMBERS = [*'0'..'9']

  def initialize
    @name = create_aleat_name
  end

  def name
    @name
  end

  def reset
    @name = create_aleat_name
  end

  private
  def create_aleat_name
    [CHARS.sample(2) + NUMBERS.sample(3)].join
  end

end
