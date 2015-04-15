class Grains
  def initialize
    @arr = Array.new(64) { |index| 2 ** index }
  end

  def square(count)
    @arr[count-1]
  end

  def total
    @arr.reduce(:+)
  end
end
