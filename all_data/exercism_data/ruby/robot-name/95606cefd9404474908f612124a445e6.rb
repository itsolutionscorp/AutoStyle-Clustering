class Robot

  attr_reader :name

  def initialize
    chars = ('A'..'Z').to_a
    digits = ('1'..'9').to_a
    @name = chars.sample+
            chars.sample+
            digits.sample+
            digits.sample+
            digits.sample
  end

  def reset
    initialize
  end

end
