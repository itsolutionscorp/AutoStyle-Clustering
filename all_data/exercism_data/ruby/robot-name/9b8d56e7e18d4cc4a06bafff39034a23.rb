class Robot
  WORD_CHARACTERS = [('a'..'z'), ('A'..'Z')].map(&:to_a).flatten.push('_')
  DIGITS = ('0'..'9').to_a

  def name
    @name ||= (WORD_CHARACTERS.sample(2) + DIGITS.sample(3)).join
  end

  def reset
    @name = nil
  end
end
