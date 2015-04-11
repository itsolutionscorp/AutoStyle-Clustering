class Robot
  LETTERS=('A'..'Z').to_a
  NUMBERS=(0..9).to_a

  def name
    @name ||= LETTERS.sample(2) + NUMBERS.sample(3)
  end

  def reset
    @name = nil
  end
end
