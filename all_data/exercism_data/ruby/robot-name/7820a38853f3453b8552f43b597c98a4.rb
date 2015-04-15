# require "Random"
require "Set"

class Robot
  @@used_names = Set.new

  def self.generate_name
    prng = Random.new
    characters = ('A'..'Z').to_a
    digits     = ('0'..'9').to_a
    name       = ""

    begin
      name = ""
      2.times { name << characters[prng.rand(26)] }
      3.times { name << digits[prng.rand(10)] }
    end while @@used_names.member?(name)

    @@used_names << name
    name
  end

  def reset
    @name = nil
  end

  def name
    @name ||= self.class.generate_name
  end
end
