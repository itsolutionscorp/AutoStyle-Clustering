class Grains
  def total
    result = 0
    (0..63).each do |i|
      result += 2 ** i
    end
    result
  end

  def square(input)
    2 ** (input-1)
  end
end
