require 'set'

class RandomStringGenerator
  attr_reader :format_map, :format

  # format string default:
  # @ for letter
  # # for number
  def initialize(format = '@@###', format_map = { '@' => ('A'...'Z'),
                                                  '#' => (0..9) })
    @format = format.chars
    @format_map = format_map.each { |(k, v)| format_map[k] = v.to_a }
  end

  def convert_format(char)
    format_map[char].sample.to_s
  end

  def generate
    format.reduce('') do |string, char|
      string + convert_format(char)
    end
  end
end

class RobotRegistry
  attr_reader :name_generator

  def initialize(name_generator = RandomStringGenerator.new)
    @name_generator = name_generator
  end

  def assign
    name_generator.generate
  end
end

class UniqueRobotRegistry < RobotRegistry
  def used_names
    @used_names ||= Set.new
  end

  def assign
    potential_name = ''

    loop do
      potential_name = super
      break unless used_names.add?(potential_name).nil?
    end

    potential_name
  end
end

ROBOT_REGISTRY ||= UniqueRobotRegistry.new

class Robot
  attr_reader :name

  def initialize(registry = ROBOT_REGISTRY)
    @registry = registry
    reset
  end

  def reset
    @name = @registry.assign
  end
end
