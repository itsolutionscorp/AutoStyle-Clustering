class Robot
  attr_reader :name

  @robot_names = []

  def self.robot_names
    @robot_names
  end

  def initialize
    @name = new_name
    Robot.robot_names << @name
  end

  def reset
    initialize
  end

  private

  def new_name
    name = (0..1).map { ("A".."Z").to_a.sample }.join + (0..2).map { rand(10) }.join
    Robot.robot_names.include?(name) ? new_name : name
  end
end
