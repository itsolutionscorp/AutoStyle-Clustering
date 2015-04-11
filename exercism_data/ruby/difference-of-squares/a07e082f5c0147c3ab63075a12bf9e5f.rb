class Squares
  def initialize(numbers)
    @numbers = numbers #Assign to local variable
  end

  def square_of_sums
    sum=0

    @numbers.times do |i| #add each number to the total sum
      sum += i+1
    end

    sum**2 #square the sum
  end

  def sum_of_squares
    sum=0

    @numbers.times do |i| #add the square of each number to the total sum
      sum += (i+1)**2
    end

    sum
  end

  def difference
    (sum_of_squares - square_of_sums).abs #absolute value of difference between the two methods
  end
end
