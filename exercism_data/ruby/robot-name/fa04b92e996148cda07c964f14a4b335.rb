class Robot
  def initialize
    reset
  end

  def name
    @name ||= generate_name
  end

  def reset
    @name = nil
  end

  private

  CHARS   = ("A" .. "Z").to_a * 2
  DIGITS  = (0 .. 9).to_a * 3

  # This generator may cause collisions.
  def generate_name
    (CHARS.sample(2) + DIGITS.sample(3)).join
  end
end
