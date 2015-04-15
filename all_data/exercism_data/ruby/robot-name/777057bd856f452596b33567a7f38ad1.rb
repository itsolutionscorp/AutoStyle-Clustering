class Robot
  def name
    @name ||="#{alpha}#{numeric}"
  end

  def alphabet
    ('a'..'z').to_a + ('A'..'Z').to_a
  end

  def alpha
    alphabet.sample(2).join('')
  end

  def numeric
    rand(100..999)
  end

  def reset
    @name = nil
  end
end
