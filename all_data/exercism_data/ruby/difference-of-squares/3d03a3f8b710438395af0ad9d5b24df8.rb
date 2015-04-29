class Squares
  @@square_number = 0
  def initialize(square_number)
    @@square_number = square_number
  end

  def difference
    (sum_of_squares - square_of_sums).abs
  end

    def sum_of_squares
      ret = 0
      (1..@@square_number).each do |i|
        ret += i**2
      end
      ret
    end

    def square_of_sums
      ret = 0
      (1..@@square_number).each do |i|
        ret += i
      end
      ret ** 2
    end
end
