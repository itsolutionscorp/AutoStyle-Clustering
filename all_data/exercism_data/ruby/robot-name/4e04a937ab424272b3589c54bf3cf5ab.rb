class Robot
  NAMES_IN_USE = []

  def name
    @name ||= generate_name
  end

  def reset
    NAMES_IN_USE.delete(@name)
    @name = generate_name
  end

protected

  def generate_name
    name = [letter, letter, number, number, number].join

    if NAMES_IN_USE.include?(name)
      generate_name
    else
      NAMES_IN_USE << name
      name
    end
  end

  def letter
    ("A".."Z").to_a.sample
  end

  def number
    (0..9).to_a.sample
  end
end
