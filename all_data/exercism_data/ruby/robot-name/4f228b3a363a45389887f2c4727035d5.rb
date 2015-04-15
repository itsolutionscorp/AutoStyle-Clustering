class Robot

  def name
    letters = [*'A'..'Z']
    numbers = [*'0'..'9']
    random_robot = 2.times.map {letters.sample}.join + 3.times.map {numbers.sample}.join
    @robots ||= []
    @robots << random_robot
    if @toggle
      @toggle = false
      @robots.last
    else
      @robots.first
    end
  end

  def reset
    @toggle = true
    Robot.new.name
  end


end
