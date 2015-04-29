require 'set'

class Robot
  @assigned_names = Set.new

  def name
    @name ||= generate_name
  end

  def reset
    @name = nil
  end

  private

  def generate_name
    name = [chars, digits].join
    self.class.assigned_name?(name) ? generate_name : name
  end

  def self.assigned_name?(name)
    @assigned_names.include?(name)
  end

  def chars
    [rand_char, rand_char].join
  end

  def digits
    [rand(10), rand(10), rand(10)].join
  end

  def rand_char
    (97 + rand(25)).chr.upcase
  end
end
