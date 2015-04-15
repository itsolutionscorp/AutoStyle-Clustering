class Robot

  def initialize
    @@robots ||= Array.new
  end

  def name
    @robot ||= new_robot
  end

  def reset
    temp = @robot
    until temp != @robot do
      @robot = new_robot
    end
    @robot
  end

  def new_robot
    @robot = [*'a'..'z', *'A'..'Z'].sample + [*'a'..'z', *'A'..'Z'].sample \
    + [*0..9].sample.to_s + [*0..9].sample.to_s + [*0..9].sample.to_s
  end

end
