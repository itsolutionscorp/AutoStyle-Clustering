class Robot

  attr_reader :name

  def initialize
    @name = generate_name
  end

  def reset
    @name = generate_name
  end

  private

  def generate_name
    value = ""
    letters = ('A'..'Z').to_a
    2.times { value << letters[rand(26)] }
    3.times { value << rand(10).to_s }
    value
  end

end
