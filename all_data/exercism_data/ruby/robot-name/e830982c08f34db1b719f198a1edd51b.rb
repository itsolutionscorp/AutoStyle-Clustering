class Robot
  attr_reader :letters, :digits

  def initialize
    @lowercase_letters = ('a'..'z').to_a
    @more_letters = ('A'..'Z').to_a

    @letters = @lowercase_letters + @more_letters 
    @digits = (0..9).to_a
    @rob_name = []
  end

  def name
    @name ||=
    @rob_name << (letters.sample(2) + digits.sample(3)).join
    @rob_name.pop
  end

  def reset
    remove_instance_variable(:@name)
  end

end
