require 'set'

class Robot
  attr_reader :name
  TAKEN_NAMES = Set.new

  def initialize
    reset
  end

  def reset
    @name = generate_name
  end

  private

  def generate_name
    loop do
      name = [('A'..'Z').to_a.sample(2), ('0'..'9').to_a.sample(3)].join
      unless TAKEN_NAMES.include?(name)
        TAKEN_NAMES << name
        return name
      end
    end
  end
end
