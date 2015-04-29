class Robot
  def initialize(registry = RobotDesignationRegistry.new)
    @registry = registry
  end

  def name
    @name ||= generate_and_register_name
  end

  def reset
    @name = nil
  end

  private

  def generate_and_register_name
    @registry.register(generate_name)
  rescue NameAlreadyRegisteredError
    retry
  end

  def generate_name
    series = 2.times.map { ('A'..'Z').to_a.sample }
    serial = 3.times.map { rand(10) }
    (series + serial).join
  end

end

class NameAlreadyRegisteredError < RuntimeError; end;

class RobotDesignationRegistry
  attr_accessor :registry

  def initialize
    @registry = []
  end

  def register(designation)
    raise NameAlreadyRegisteredError if registry.include?(designation)

    registry << designation
    designation
  end
end
