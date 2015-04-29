class Robot
  attr_reader :name
  @@robot_names = ['_robot']

  def self.robot_names
    @@robot_names[1..-1]
  end

  def initialize
    @name = @@robot_names.first
    new_name
  end

  def reset
    new_name
  end

  private

  def new_name
    sample_name = [*'A'..'Z'].sample(2).join + [*'0'..'9'].sample(3).join
    new_name until !@@robot_names.include? sample_name
    @name = sample_name
    @@robot_names << name
  end
end

r = Robot.new
p r.name # "RT592"
r.reset
p r.name # "RB972"
p Robot.robot_names # ["RT592", "RB972"]

# 1000.times do
#   Robot.new
# end # OK
# p Robot.robot_names == Robot.robot_names.uniq # true
