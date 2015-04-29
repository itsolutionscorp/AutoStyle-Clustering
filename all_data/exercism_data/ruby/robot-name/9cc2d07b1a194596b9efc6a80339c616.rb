class Robot
  @@list = {}

  def initialize
    check_availablity
  end

  def name
    @name || assign_name
  end

  def reset
    @name = nil
  end

  def self.reset_names
    @@list = {}
  end

  def self.available?
    @@list.count < 676  # 26 * 26 * 10 * 10 * 10 = 676_000
  end

  private

  def assign_name
    check_availablity
    loop do
      @name = random_robot_name
      break unless name_taken? @name
    end
    @@list[@name] = nil
    @name
  end

  def name_taken?(name)
    @@list.include? name
  end

  def random_robot_name
    random_2_letter_string + random_3_digit_number_string
  end

  def random_2_letter_string
    (1..2).map { (65 + rand(26)).chr }.join
  end

  def random_3_digit_number_string
    r = rand(0..999)
    nb_digits = Math.log10(r + 0.9).to_i + 1
    '0' * (3 - nb_digits) + r.to_s
  end

  def check_availablity
    fail StandardError, 'No more names available.' unless Robot.available?
  end
end

require 'minitest/autorun'

class RobotTest < MiniTest::Unit::TestCase
  def test_deplete_robot_names
    Robot.reset_names
    robot = Robot.new
    999.times {
      robot.reset
      robot.name
    }
  rescue StandardError
    assert !Robot.available?
  end
end
