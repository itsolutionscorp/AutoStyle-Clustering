require "delegate"

class Year < SimpleDelegator
  def leap?
    multiple_of?(4) and not multiple_of?(100) or multiple_of?(400)
  end

  private

  def multiple_of? divisor
    (self % divisor).zero?
  end
end
