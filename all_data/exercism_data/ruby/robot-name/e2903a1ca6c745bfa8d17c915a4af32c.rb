require 'set'

ROBOT_NAMES_IN_USE = Set.new

class Robot
  def initialize name_generator = NameGenerator.new, names_in_use = ROBOT_NAMES_IN_USE
    @name_generator = name_generator
    @names_in_use = names_in_use
    assign_new_name
  end

  attr_reader :name

  def reset
    @names_in_use.delete @name
    assign_new_name
  end

  private  

  def assign_new_name
    begin
      new_name = @name_generator.generate
    end until !@names_in_use.include? new_name

    @names_in_use.add new_name
    @name = new_name
  end
end

class NameGenerator
  LETTERS = ('A'..'Z').to_a
  DIGITS  = ('0'..'9').to_a

  def generate
    (LETTERS.sample(2) + DIGITS.sample(3)).join
  end
end