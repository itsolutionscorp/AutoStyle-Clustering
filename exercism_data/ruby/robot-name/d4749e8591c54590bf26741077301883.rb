require 'set'

class Robot
  DIGITS = (0..9).to_a
  CHARS = ("A".."Z").to_a

  @assigned_names = Set.new

  def name
    @name ||= generate_name
  end

  def reset
    @name = nil
  end

  private

  def generate_name
    name = [rand_chars, rand_digits].join
    self.class.assigned_name?(name) ? generate_name : name
  end

  def self.assigned_name?(name)
    @assigned_names.include?(name)
  end

  def rand_chars
    CHARS.sample(2)
  end

  def rand_digits
    DIGITS.sample(3)
  end
end
