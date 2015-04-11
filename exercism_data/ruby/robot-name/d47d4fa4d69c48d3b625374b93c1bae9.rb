require 'set'

# A class made to handle naming robots.
class Robot
  attr_reader :name
  @robot_names = Set.new

  def initialize
    @names_list = Robot.instance_variable_get(:@robot_names)
    self.name = generate_name
  end

  def reset
    @names_list.delete(name)
    Robot.instance_variable_set(:@robot_names, @names_list)
    self.name = generate_name
  end

  private

  attr_writer :name

  def generate_name
    robo_name = ''
    loop do
      2.times { robo_name << generate_random_letter }
      robo_name << generate_random_number
      break unless @names_list.include? robo_name
    end
    robo_name
  end

  def generate_random_letter
    rand(65..90).chr
  end

  def generate_random_number
    rand(100..999).to_s
  end
end
