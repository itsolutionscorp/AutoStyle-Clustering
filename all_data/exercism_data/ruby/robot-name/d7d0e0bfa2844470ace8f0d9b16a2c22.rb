require 'set'

class Robot
  @@used_names = Set.new

  def initialize
    reset
  end

  def name
    @name
  end

  def reset
    Robot.deregister_name_to_factory_floor @name
    new_name = generate_unique_name
    Robot.register_name_to_factory_floor new_name
    @name = new_name
  end

  def self.get_factory_network_names
    @@used_names
  end

  def self.register_name_to_factory_floor(new_name)
    @@used_names.add(new_name)
  end

  def self.deregister_name_to_factory_floor(name)
    @@used_names.delete(name)
  end

private
  def generate_unique_name
    a_name = generate_name
    while(Robot.get_factory_network_names.include? a_name)
      a_name = generate_name
    end

    return a_name
  end

  def generate_name
    ([*('A'..'Z')]).sample(2).join + ([*(0..9)]).sample(3).join
  end
end
