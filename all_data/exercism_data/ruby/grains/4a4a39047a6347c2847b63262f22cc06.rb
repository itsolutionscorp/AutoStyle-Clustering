class Grains
  def square(num)
    result = 1
    if num > 1
      occurences = num - 1
      occurences.times do
        result = result * 2
      end
    end
    result
  end
  def total
    total = 1
    64.times do |index|
      total = total + square(index + 1)
    end
    total = total - 1
  end
end
