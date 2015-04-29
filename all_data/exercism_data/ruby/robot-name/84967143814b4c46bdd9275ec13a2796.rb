class Robot
  attr_reader :name
  @@names = []
  def initialize()
    @name = generate_name
    @@names << @name
  end
  def reset()
    @name = generate_name
    @@names << @name
  end
  def generate_name()
    new_rand_name = ('A'..'Z').to_a.sample(2).join << rand(1000).to_s.rjust(3, "0")
    @@names.find_index(new_rand_name) == nil ? new_rand_name : new_rand_name = generate_name()
  end
  def self.stored_names()
    @@names
  end
end
