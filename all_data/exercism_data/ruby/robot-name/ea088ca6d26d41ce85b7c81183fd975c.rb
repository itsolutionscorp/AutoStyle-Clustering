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
    [LETTERS.sample(2), DIGITS.sample(3)].join
  end
end
