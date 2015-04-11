class Grains
  def square n
    return 1 if n == 1
    return 2 if n == 2
    number = 2
    (n-2).times do |i|
      number = number * 2
    end
    number
  end
  def total
    runningTotal = 0
    [ *1..64 ].each do |i|
      runningTotal += square i
    end
    runningTotal
  end
end
