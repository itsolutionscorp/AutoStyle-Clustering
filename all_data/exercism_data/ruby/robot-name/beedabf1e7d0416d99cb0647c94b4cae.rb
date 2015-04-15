class Robot
  def name
    @name ||= generate_and_register_name
  end

  def reset
    RobotDesignationRegistry::unregister(@name)
    @name = nil
  end

  private

  def generate_and_register_name
    RobotDesignationRegistry.register(generate_name)
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
  @@registry = []

  def self.register(designation)
    raise NameAlreadyRegisteredError if @@registry.include?(designation)
    @@registry << designation
    designation
  end

  def self.unregister(designation)
    @@registry.delete designation
  end
end
