require 'set'

class Robot
  attr_reader :name

  def initialize(name_factory = RandomRobotNameFactory)
    @name_factory = UniqueFactory.new(name_factory)
    reset
  end

  def reset
    name_factory.recycle(name)
    @name = name_factory.build
  end

  private

  attr_reader :name_factory
end

class RandomRobotNameFactory
  def self.build
    random_name
  end

  def self.maximum_available_objects
    MAXIMUM_NUMBER_OF_NAMES
  end

  MAXIMUM_NUMBER_OF_NAMES = ('AA000'..'ZZ999').size

  def self.random_name
    random_alpha_prefix + random_numeric_suffix
  end

  def self.random_alpha_prefix
    ('AA'..'ZZ').to_a.sample
  end

  def self.random_numeric_suffix
    ('000'..'999').to_a.sample
  end
end

class UniqueFactory
  ObjectLimitError = Class.new(StandardError)

  def initialize(factory)
    @existing_objects = Set.new
    @factory = factory
  end

  def build
    fail ObjectLimitError, "#{factory.class} Class"  if no_more_objects
    while existing_objects.member?(object = factory.build); end
    existing_objects.add(object)
    object
  end

  def recycle(object)
    existing_objects.delete(object)
  end

  private

  def no_more_objects
    existing_objects.size == @factory.maximum_available_objects
  end

  attr_reader :existing_objects, :factory
end
