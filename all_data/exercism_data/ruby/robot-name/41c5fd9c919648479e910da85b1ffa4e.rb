require 'set'

class Robot
  def name
    @name ||= UniqueNameGenerator.generate
  end

  def reset
    @name = nil
  end
end


class UniqueNameGenerator
  DIGITS = ('0'..'9').to_a
  LETTERS = ("A".."Z").to_a

  def self.generate
    name = generate_name
    name = generate_name while generated?(name)
    add(name) && name
  end

  def self.generated_names
    @generated_names ||= Set.new
  end

  private

  def self.generated?(name)
    generated_names.include?(name)
  end

  def self.add(name)
    generated_names.add(name)
  end

  def self.generate_name
    [rand_letters, rand_digits].flatten.join
  end

  def self.rand_letters
    [LETTERS.sample(1), LETTERS.sample(1)]
  end

  def self.rand_digits
    [DIGITS.sample(1), DIGITS.sample(1), DIGITS.sample(1)]
  end
end
