class Grains
  def initialize
    @store = [1]
  end

  def square(num)
    fill_squares(num)
    @store[num - 1]
  end

  def total
    fill_squares(63)
    @store.inject(:+)
  end

  private

  def fill_squares(num)
    num.times { |i| @store << @store.last * 2 }
  end
end
