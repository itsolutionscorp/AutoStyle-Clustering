require 'set'

class Robot

  attr_reader :name

  def initialize
    @@name_set ||= Set.new
    @name = generate_name
  end

  def reset
    @@name_set.delete(@name)
    @name = generate_name
  end

  private

  def generate_name
    value = ( ('A'..'Z').to_a.sample(2) + (0..9).to_a.sample(3) ).join
    if @@name_set.add?(value)
      value
    else
      generate_name
    end
  end

end
