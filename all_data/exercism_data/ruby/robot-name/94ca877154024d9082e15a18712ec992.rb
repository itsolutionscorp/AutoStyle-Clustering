class Robot
  attr_reader :name

  def initialize
    @name = ''
    generate_name
  end

  def generate_name
    2.times { @name << ('A'..'Z').to_a.sample }
    3.times { @name << (0..9).to_a.sample.to_s }
  end

  def reset
    @name = ''
    generate_name
  end
end
