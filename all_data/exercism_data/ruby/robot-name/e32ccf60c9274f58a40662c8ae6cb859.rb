class Robot
  LETTERS = ('AA'..'ZZ').to_a
  NUMBERS = ('000'..'999').to_a

  def name
    @name ||= randomize_name
  end

  def randomize_name
    @name = ''
    @name << LETTERS.sample
    @name << NUMBERS.sample
  end

  def reset
    @name = nil
  end
end
