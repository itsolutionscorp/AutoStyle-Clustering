class Robot
  def name
    @name ||= generate_name
  end

  def reset
    @name = nil
  end

  private

  def generate_name
    series = (65..90).map(&:chr).shuffle.take(2).join
    serial = rand(100..999).to_s

    series + serial
  end
end
