class Robot
  def initialize
    assign_new_name
  end

  attr_reader :name

  def reset
    assign_new_name
  end

  private 

  LETTERS = ('A'..'Z').to_a
  DIGITS  = ('0'..'9').to_a

  def assign_new_name
    @name = (LETTERS.sample(2) + DIGITS.sample(3)).join
  end
end
