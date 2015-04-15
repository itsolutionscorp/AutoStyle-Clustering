class Robot
  attr_accessor :name

  def initialize
    @name = generate_name
  end

  def generate_name
    name = ''
    letters = ('A'..'Z').to_a
    numbers = (0..9).to_a

    2.times { name << letters.sample }
    3.times { name << numbers.sample.to_s }

    name
  end

  def reset
    self.name = generate_name
  end
end
