Year = Struct.new(:number) do
  def leap?
    divisible_by(4) and not divisible_by(100) or divisible_by(400)
  end

  def divisible_by other_number
    number % other_number == 0
  end
end
