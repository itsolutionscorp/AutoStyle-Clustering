require 'set'

class Robot
  def name
    @name ||= generate_name
  end

  def reset
    @name = nil
  end

  private

  def generate_name
    UniqueNameGenerator.generate
  end
end


class UniqueNameGenerator
  DIGITS = ("0".."9").to_a
  LETTERS = ("A".."Z").to_a

  def self.generate
    name = generate_name
    name = generate_name while already_in_use?(name)
    add(name) && name
  end

  def self.already_in_use?(name)
    generated_names.include?(name)
  end

  class << self
    private
    
    def generated_names
      @generated_names ||= Set.new
    end

    def add(name)
      generated_names.add(name)
    end

    def generate_name
      [rand_letters, rand_digits].flatten.join
    end

    def rand_letters
      [LETTERS.sample(1), LETTERS.sample(1)]
    end

    def rand_digits
      [DIGITS.sample(1), DIGITS.sample(1), DIGITS.sample(1)]
    end
  end
end
