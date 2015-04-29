class Grains

  BOARD_SIZE = 64
  MULTIPLIER = 2

  def initialize
    @num_array = [1]
    (1..(BOARD_SIZE - 1 )).each do |n|
      @num_array << (@num_array[n-1])*MULTIPLIER
    end
  end

  def square(number)
    @num_array[number - 1]
  end

  def total
    @num_array.inject { |sum, x| sum + x }
  end
end
