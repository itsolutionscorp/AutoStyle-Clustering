require 'set'
# A class made to handle naming robots.
class Robot
  public

  attr_reader :name
  @@robot_names = Set.new

  def initialize
    self.name = generate_name
  end

  def reset
    @@robot_names.delete(name)
    self.name = generate_name
  end

  private

  attr_writer :name

  def generate_name
    robo_name = ''
    loop do
      2.times { robo_name << generate_random_letter }
      robo_name << generate_random_number
      break unless @@robot_names.include? robo_name
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
