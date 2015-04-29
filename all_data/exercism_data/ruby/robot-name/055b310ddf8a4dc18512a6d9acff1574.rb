class Robot
  @@used_names = []

  def initialize
    @name = self.get_name
  end

  def name
    @name
  end

  def reset
    @name = self.get_name
  end

  def get_name
    robot_name = ""

    loop do
      robot_name = generate_name
      break if @@used_names.include?(robot_name) == false
    end

    add_used_name( robot_name )

    robot_name
  end

  def add_used_name( robot_name )
    @@used_names.push( robot_name )
  end

  def generate_name
    (0..1).map { ('A'..'Z').to_a[rand(26)] }.join + rand(100..999).to_s
  end
end
