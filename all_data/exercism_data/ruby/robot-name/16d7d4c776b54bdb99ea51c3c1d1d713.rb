class RobotNameGenerator
  @names = nil

  def self.request_name
    initialize_names unless @names
    @names[@name_index += 1]
  end

  def self.initialize_names
    @name_index = -1
    @names = generate_all_names
  end

  def self.generate_all_names
    prefixes.product(suffixes)
      .map {|name_pair| name_pair.join}
      .shuffle
  end

  def self.prefixes
    ('A'..'Z').to_a
      .permutation(2)
      .map {|two_letters| two_letters.join}
  end

  def self.suffixes
    1000.times
      .map {|number| "%03d"%number}
  end
end

class Robot
  attr_reader :name

  def initialize(namer=RobotNameGenerator)
    @namer = namer
    get_name
  end

  def reset
    get_name
  end

  def get_name
    @name = @namer.request_name
  end
end
