class Robot
  ALPHABET = ('A'..'Z').to_a
  NUMERIC  = (1..9).to_a
  
  def reset
    @last_name = @name
    @name      = nil
  end

  def name
    @name ||= generate_name
  end

  private
  def generate_name
    alph = ALPHABET.dup.shuffle[0,2].join
    num  = NUMERIC.dup.shuffle[0,3].join
    result = "#{alph}#{num}"
    result = generate_name if result == @last_name
    result
  end
end
