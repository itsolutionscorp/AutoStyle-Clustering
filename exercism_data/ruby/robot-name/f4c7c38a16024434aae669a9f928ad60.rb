require 'set'
require 'singleton'

class Robot
  def name
    @name ||= RobotNamePool.unique_name
  end

  def reset
    RobotNamePool.release_name(@name)
    @name = nil
  end
end

class RobotNamePool
  include Singleton

  MAXIMUM_NUMBER_OF_NAMES = ('AA000'..'ZZ999').size
  NameLimitError = Class.new(StandardError)
  attr_reader existing_names

  def initialize
    @existing_names = Set.new
  end

  def unique_name
    fail NameLimitError, "Out of unique names."  if no_more_names

    while existing_names.member?(name = build_name); end
    existing_names.add(name)
    name
  end

  def release_name(name)
    existing_names.delete(name)
  end

  private
  def no_more_names
    existing_names.size == MAXIMUM_NUMBER_OF_NAMES
  end

  def build_name
    random_alpha_prefix + random_numeric_suffix
  end

  def random_alpha_prefix
    ('AA'..'ZZ').to_a.sample
  end

  def random_numeric_suffix
    ('000'..'999').to_a.sample
  end
end
