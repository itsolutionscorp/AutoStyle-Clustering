class Year
  DIVISIBLE = [4, 100, 400]

  def self.leap? year
    eval_leap(year)
  end

  private

  def self.eval_leap year
    states = divisible_states(year)
    state = states[0] unless states[1]
    state.nil? ? states[2] : state
  end

  def self.divisible_states year
    DIVISIBLE.map { |mod| divisible?(year, mod) }
  end

  def self.divisible? year, mod
    year % mod == 0
  end
end
