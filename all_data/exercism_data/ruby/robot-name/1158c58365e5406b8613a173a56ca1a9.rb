class Robot
  attr_accessor :name

  def initialize
    @name = reset
  end

  def set_name
    letters = [*('a'..'z'),*('A'..'Z')]
    numbers = [*(0..9)]
    name = ""
    2.times { name << letters.sample }
    3.times { name << numbers.sample.to_s }
    name
  end

  def reset
    @name = set_name
  end

end
