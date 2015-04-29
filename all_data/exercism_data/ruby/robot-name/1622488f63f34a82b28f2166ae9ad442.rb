class Robot
  attr_reader :name

  def initialize
    reset
  end

  def reset
    @name = generate_name
  end

  def generate_name
    name = ""
    2.times { name += ('A'..'Z').to_a.sample }
    3.times { name += rand(0..9).to_s }
    name
  end
end
