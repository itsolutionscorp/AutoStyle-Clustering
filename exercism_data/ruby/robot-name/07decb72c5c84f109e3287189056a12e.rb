class Robot
  def name
    @name ||= OtherRobotNameGenerator.generate
  end

  # This should really release a name back to the pool of names
  def reset
    @name = nil
  end
end

class OtherRobotNameGenerator
  ALPHA = ('A'..'Z').to_a
  NUMBER = ('0'..'9').to_a
  TEMPLATE = [ALPHA, ALPHA, NUMBER, NUMBER, NUMBER]

  attr_accessor :db

  def self.generate
    @generator ||= new
    @generator.generate
  end

  def initialize
    self.db = {}
  end

  def generate
    name = random_name
    while db.has_key? name
      name = random_name
    end
    db[name] = name
    name.join ''
  end

  def random_name
    TEMPLATE.map {|choices| choices[Random.rand(choices.length)] }
  end
end
