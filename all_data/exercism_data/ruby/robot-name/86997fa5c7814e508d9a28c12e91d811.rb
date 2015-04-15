class Robot
  @@all_names = {}
  
  def name
    @name ||= generate_name
  end

  def reset
    @name = nil
  end

  private

  def generate_name
    letters = (0..1).map { (65 + rand(26)).chr }.join
    numbers = (0..2).map { rand(9) }.join
    robot_name = letters + numbers

    generate_name if @@all_names[robot_name]

    @@all_names[robot_name] = true
    robot_name
  end
end
