class Robot
  def name
    @name ||= generate
  end

  def reset
    @name = nil
  end

private

  def generate
    random_letters + random_digits
  end

  def random_letters
    random_string(2) { [*"A".."Z"].sample }
  end

  def random_digits
    random_string(3) { [*0..9].sample }
  end

  def random_string(length, &block)
    length.times.map(&block).join
  end

end
