class Year
  
  def initialize(value)
    @value = value
  end

  def leap?
    evenly_divisible_by?(4) and (!evenly_divisible_by?(100) or evenly_divisible_by?(400))
  end

  private

    attr_reader :value

    def evenly_divisible_by?(divisor)
      (value % divisor) == 0
    end

end
