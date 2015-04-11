class Robot
  attr_reader :name
  @@names = []
  def initialize()
    @name = generate_name
  end
  def reset()
    @name = generate_name
  end
  def generate_name()
    is_unique = false
    while is_unique == false do
      new_rand_name = 2.times.map{ |i| (65+rand(26)).chr }.join << rand(999).to_s.rjust(3, "0")
      is_unique = @@names.find_index(new_rand_name) == nil
    end
    @@names << new_rand_name
    new_rand_name
  end
end
