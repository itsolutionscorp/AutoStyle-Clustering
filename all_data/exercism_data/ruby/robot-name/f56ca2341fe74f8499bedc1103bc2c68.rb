class Robot
  attr_reader :name

  def initialize
    generate_name
  end

  def reset
    generate_name
  end

  private

  def generate_name
    @name = ""
    2.times { @name << letter }
    3.times { @name << digit  }
  end

  def letter
    rand(65..90).ord
  end

  def digit
    rand(0..9).to_s
  end
end
