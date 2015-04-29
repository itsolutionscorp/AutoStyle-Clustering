class Robot

  def initialize
    @name = [*('A'..'Z')].shuffle.join[0..1] + [*('0'..'9')].shuffle.join[0..2]
  end

  def name
    @name
  end

  def reset
    @name = Robot.new.name
  end
end
