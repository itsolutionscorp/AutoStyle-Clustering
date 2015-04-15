class Robot
  
  def initialize
    @robot_name = rando_string
  end

  def reset
    initialize
  end

  def name
    @robot_name
  end

  def rando_string
    numeric = (0..1).map {('A'..'Z').to_a[rand(26)]}.join + (0..2).map {rand(10)}.join
  end

end
