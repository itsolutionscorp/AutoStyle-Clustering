class Grains
  def square(num)
    create_grain_array[num - 1]
  end

  def total
    create_grain_array.inject{|sum,x| sum + x }
  end

  private

  def create_grain_array
    result = 1
    (1...64).to_a.map do |num|
      result = result * 2
    end.unshift(1)
  end
end
