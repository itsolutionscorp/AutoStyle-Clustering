class Robot
  def initialize
    new_name
  end

  def new_name
    chars = (0..1).map { (65 + rand(26)).chr }.join
    nums = rand(1000..9999).to_s.chars.reverse.take(3).join
    @robot_name = chars + nums
  end

  def name
    @robot_name || new_name
  end

  def reset
    @robot_name = nil
  end
end
