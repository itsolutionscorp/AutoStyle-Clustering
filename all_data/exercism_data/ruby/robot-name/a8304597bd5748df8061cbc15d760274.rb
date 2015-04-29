class Robot
  def name
    @name ||= generate
  end

  def reset
    @name = nil
  end

private

  def generate
    (Array.new(2) { random_letter } + Array.new(3) { random_digit }).join
  end

  def random_letter
    [*"A".."Z"].sample
  end

  def random_digit
    [*0..9].sample
  end

end
