class Robot

  attr_reader :name

  def initialize
    set_name
  end

  def reset
    set_name
  end

private

  def set_name
    alphabet = ('A'..'Z').to_a
    letters = alphabet.sample(2).join('')
    numbers = rand(100..999)
    @name = "#{letters}#{numbers}"
  end

end
