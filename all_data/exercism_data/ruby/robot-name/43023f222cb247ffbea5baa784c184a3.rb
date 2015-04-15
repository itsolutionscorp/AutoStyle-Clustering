require 'set'

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
  MAXIMUM_NUMBER_OF_NAMES = ('AA000'..'ZZ999').size
  NameLimitError = Class.new(StandardError)

  class << self
    attr_accessor :existing_names
  end
  @existing_names = Set.new

  def self.unique_name
    fail NameLimitError, "Out of unique names."  if no_more_names

    while existing_names.member?(name = build_name); end
    existing_names.add(name)
    name
  end

  def self.release_name(name)
    existing_names.delete(name)
  end

  private
  def self.no_more_names
    existing_names.size == MAXIMUM_NUMBER_OF_NAMES
  end

  def self.build_name
    random_alpha_prefix + random_numeric_suffix
  end

  def self.random_alpha_prefix
    ('AA'..'ZZ').to_a.sample
  end

  def self.random_numeric_suffix
    ('000'..'999').to_a.sample
  end
end
