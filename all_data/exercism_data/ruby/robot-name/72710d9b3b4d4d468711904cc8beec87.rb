class Robot
  LETTERS = ('A'..'Z').to_a
  NUMBERS = (1..9).to_a

  def name
    @name ||= [prefix, suffix].join
  end

  def reset
    @name = nil
  end

  private

  def prefix
    LETTERS.sample 2
  end

  def suffix
    NUMBERS.sample 3
  end
end
