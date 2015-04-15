class Robot
  NAMES_IN_USE = []

  def name
    @name ||= generate_name
  end

  def reset
    @name = generate_name
  end

protected

  def generate_name
    name = [
      letters.sample,
      letters.sample,
      numbers.sample,
      numbers.sample,
      numbers.sample
    ].join("")

    if NAMES_IN_USE.include?(name)
      generate_name
    else
      NAMES_IN_USE << name
      name
    end
  end

  def letters
    @letters ||= ("A".."Z").to_a
  end

  def numbers
    @numbers ||= (0..9).to_a
  end
end
