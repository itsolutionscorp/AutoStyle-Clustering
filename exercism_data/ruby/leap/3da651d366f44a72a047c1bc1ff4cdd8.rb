class Year
  def initialize number
    @number = number
  end

  def leap?
    multiple_of?(4) unless multiple_of?(100) and not multiple_of?(400)
  end

  def to_i
    @number
  end

  private

  def multiple_of? other_number
    (self.to_i % other_number).zero?
  end
end
