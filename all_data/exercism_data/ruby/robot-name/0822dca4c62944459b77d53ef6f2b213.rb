class Robot
  LETTERS = ('A' .. 'Z').to_a
  NUMBERS = (0..9).to_a

  def name
    @name ||= random_string(LETTERS, 2) + random_string(NUMBERS, 3)
  end

  def reset
    @name = nil
  end

  def random_string set, length
    length.times.map { set.to_a.sample }.join
  end

end
