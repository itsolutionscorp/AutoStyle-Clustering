class Robot

  @@taken_names = []

  def name
    @name ||= create_name
  end

  # created setter method to test name collisions
  def name=(name)
    unless name_taken?(name)
       @name = name 
       @@taken_names << name
    end
  end

  def reset
    @name = nil
  end

  def self.taken_names
    @@taken_names
  end

  private

  LETTERS = [*'a'..'z'] + [*'A'..'Z']

  def generate_random
    letters  = LETTERS.sample + LETTERS.sample
    digits   = rand(1000).to_s.ljust(3,'0')
    name     = letters + digits
  end

  def create_name
    name = generate_random
    if name_taken?(name)
      create_name
    else
      @@taken_names << name
      name
    end
  end
 
  def name_taken?(name)
    @@taken_names.include?(name)
  end
end
