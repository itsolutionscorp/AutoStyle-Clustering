class Robot
  attr_reader :name

  def name
    @name ||= generate_name
  end

  def reset
    @name = nil
  end

  private

  def generate_name
    format("%c%c%03d", rand(65..90), rand(65..90), rand(1000))
  end
end
