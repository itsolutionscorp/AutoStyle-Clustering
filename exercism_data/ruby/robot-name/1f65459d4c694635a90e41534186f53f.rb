class Robot
  def name
    @name ||= "#{letters}#{numbers}"
  end

  def reset
    @name = nil
  end

  private

  ALPHABET = ('A'..'Z').to_a + ('a'..'z').to_a

  def letters
    ALPHABET.sample(2).join
  end

  def numbers
    rand(100..999)
  end
end
