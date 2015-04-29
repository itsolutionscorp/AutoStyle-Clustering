class Grains
  def square(number)
    (2..number).reduce(1) do |grains, _square_number|
      grains + grains
    end
  end

  def total
    (2..total_number_of_squares).reduce(1) do |grains, square_number|
      grains + self.class.new.square(square_number)
    end
  end

  private

  def total_number_of_squares
    64
  end
end
