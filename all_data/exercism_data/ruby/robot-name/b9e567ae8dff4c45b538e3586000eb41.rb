class Robot

  def name
    @name ||= generate_name
  end

  def reset
    @name = nil
  end


private
  def other_robots
    robots = []
    ObjectSpace.each_object Robot do |robot|
      next if robot == self
      robots << robot
    end
    robots
  end

  def other_robot_names
    other_robots.map(&:name)
  end

  def generate_name
     s = ""
     2.times { s << ('A'..'Z').to_a.sample }
     3.times { s << rand(9).to_s }

     if other_robot_names.include?(s)
       self.generate_name
     else
      return s
    end
  end

end
