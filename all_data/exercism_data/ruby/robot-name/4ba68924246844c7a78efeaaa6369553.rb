class Robot
  @@used_names = []

  def initialize
    reset
  end

  def name
    @name
  end

  def reset
    loop do
      @name = generate_name
      break unless @@used_names.include?( @name )
    end

    add_used_name( @name )
  end

  def add_used_name( robot_name )
    @@used_names.push( robot_name )
  end

  def generate_name
    (0..1).map { ('A'..'Z').to_a[rand(26)] }.join + rand(100..999).to_s
  end
end
