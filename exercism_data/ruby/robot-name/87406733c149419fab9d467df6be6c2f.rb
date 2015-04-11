require 'singleton'

class Robot
  def initialize(name_factory = UniqueRobotNameFactory.instance)
    @name_factory = name_factory
  end

  def name
    @name ||= @name_factory.build
  end

  def reset
    @name = nil
  end
end

class UniqueRobotNameFactory
  include Singleton

  NoMoreNamesError = Class.new(StandardError)

  VALID_NAMES = 'AA000'..'ZZ999'

  def initialize
    @names = VALID_NAMES.to_enum
  end

  def build
    begin
      @names.next
    rescue StopIteration
      fail NoMoreNamesError, "All names from #{VALID_NAMES} have been used."
    end
  end
end
