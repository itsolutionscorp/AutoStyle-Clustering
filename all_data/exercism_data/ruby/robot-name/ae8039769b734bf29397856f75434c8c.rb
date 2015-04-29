class RobotError < StandardError; end

# Generates pseudo-random robot names
#
# Example:
#   Robot.new.name #=> "aa000"
#   Robot.new.name #=> "aa001"
#   Robot.new.name #=> "aa002"
#   ...
#   Robot.new.name #=> "aa999"
#   Robot.new.name #=> "ab000"
#   Robot.new.name #=> "ab001"
#   ...
#   Robot.new.name #=> "az999"
#   Robot.new.name #=> "ba000"
#   ...
#   Robot.new.name #=> "zz999"
#   Robot.new #=> RobotError, "Max number of robots created."
class Robot
  CHARS = ('a'..'z').to_a
  DIGITS = ('0'..'9').to_a
  MAX_COUNT = CHARS.length**2 + DIGITS.length**3

  def initialize
    @@counter ||= 0
    @@counter += 1

    if @@counter >= MAX_COUNT
      raise RobotError, "Max number of robots created."
    end
  end


  def name
    @name ||= generate_name
  end

  def reset
    @@counter += 1
    @name = nil
    true
  end

  def self.counter
    @@counter
  end

  def self.reset_counter!
    @@counter = 0
  end

  private

  def generate_name
    5.times.map { |index| random(index) }.join
  end

  def random(index)
    method = index <= 1 ? :random_char : :random_digit
    send(method, index)
  end

  def random_char(index)
    CHARS[aligned_counter[index].to_i % CHARS.length]
  end

  def random_digit(index)
    DIGITS[aligned_counter[index].to_i % DIGITS.length]
  end

  def aligned_counter
    @@counter.to_s.rjust(5, '0')
  end
end
