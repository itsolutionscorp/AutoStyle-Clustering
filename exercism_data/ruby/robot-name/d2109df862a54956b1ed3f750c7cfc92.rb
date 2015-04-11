class Robot
  attr_reader :name
  @@taken_names = []
  LETTERS = ('a'..'z').to_a + ('A'..'Z').to_a

  def initialize(name=nil)
    @name = (name_taken?(name) || name.nil?) ? create_new_name : name
    add_taken(@name)
  end

  def reset
    @name = create_new_name
    add_taken(@name)
  end

  def self.taken_names
    @@taken_names
  end

  private

  def generate_random_name
    letters  = LETTERS.sample + LETTERS.sample
    digits   = rand(1000).to_s.ljust(3,'0')
    new_name = letters + digits
  end

  def name_taken?(name)
    @@taken_names.include?(name)
  end

  def add_taken(name)
    @@taken_names << name
  end

  def create_new_name
    new_name = generate_random_name
    @name = name_taken?(new_name) ? create_new_name : new_name
  end

end
