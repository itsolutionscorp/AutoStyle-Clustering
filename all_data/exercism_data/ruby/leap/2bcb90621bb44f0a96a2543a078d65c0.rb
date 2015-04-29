class Year
  def initialize number
    @number = number
  end

  def leap?
    divisible_by 4 unless divisible_by 100 and not divisible_by 400
  end

  private

  def divisible_by other_number
    (number % other_number).zero?
  end

  def number
    @number
  end
end
