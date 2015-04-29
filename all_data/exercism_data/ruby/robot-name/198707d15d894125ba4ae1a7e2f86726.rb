class Robot

  def name
    @name ||= generate_name
  end

  def reset
    @name = nil
  end

  private

  def generate_name
    two_letters + three_numbers
  end

  def two_letters
    2.times.map { [*'A'..'Z'].sample }.join
  end

  def three_numbers
    3.times.map { rand(10).to_s }.join
  end

end
