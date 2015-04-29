class Robot
  attr_reader :name

  def initialize
    reset
  end

  def reset
    @name = generate_name
  end

  private

  def generate_name
    name = ""

    5.times do |i|
      if i < 2
        name << letter
      else
        name << digit
      end
    end

    name
  end

  def letter
    rand(65..90).ord
  end

  def digit
    rand(0..9).to_s
  end
end
