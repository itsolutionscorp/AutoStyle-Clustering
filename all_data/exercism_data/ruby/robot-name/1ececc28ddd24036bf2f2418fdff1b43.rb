class Robot
  attr_reader :name

  def initialize
    @name = name_gen
  end

  def name_gen
    letters = %w(A E I Z U).shuffle.take(2).join
    numbers = %w()
      3.times do
        numbers << rand(1..9)
      end
    numbers = numbers.join
    letters + numbers
  end

  def self.name
    name
  end

  def reset
    @name = name_gen
  end

end
