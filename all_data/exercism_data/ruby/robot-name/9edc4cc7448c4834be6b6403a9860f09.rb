require 'set'

class Robot
  attr_reader :name
  @@taken_names = Set.new

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
      unless @@taken_names.include?(name)
        @@taken_names << name
        return name
      end
    end
  end
end
