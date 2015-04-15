class Robot

  def name
    @robot ||= new_robot
  end

  def reset
    temp = @robot
    until temp != @robot do
      @robot = new_robot
    end
  end

  def new_robot
    @robot = [*'a'..'z', *'A'..'Z'].sample * 2 + [*0..9].sample.to_s * 3
  end

end

# Next iteration:
# 1. make an array of robots
# 2. in the 'new_robot' method, check that the new name is not already stored in the array
# 3. in the 'reset' method, remove the old robot name from the array
# 4. (optional) create a second array to store the names taken from the 'reset' robots
