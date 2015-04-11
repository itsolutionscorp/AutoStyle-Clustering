def diff_squares(number)
  sum_of_squares = 0
  sums = 0

  x = 1

  while x <= number
    sum_of_squares += (x**2)
    sums += x
    x += 1
  end

  square_of_sums = sums**2
  diff_of_sums = square_of_sums - sum_of_squares
  diff_of_sums.abs
end

p diff_squares(100)
p diff_squares(5)
p diff_squares(10)
