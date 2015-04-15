class Robot
  def name
    @name ||= random_name
  end

  def reset
    @name = nil
  end
  
  private
    ALPHABET = ('A'..'Z').to_a
    DIGITS = (0..9).to_a.map(&:to_s)

    def random_name
      characters = ALPHABET.sample(2).join
      digits = DIGITS.sample(3).join
      characters + digits
    end
end
