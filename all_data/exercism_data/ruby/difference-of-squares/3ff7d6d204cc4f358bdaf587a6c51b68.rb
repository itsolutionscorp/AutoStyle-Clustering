class Squares
  
  attr_reader :number
  def initialize(number)
    @number = number
  end
  
  def sum_of_squares
    collect_squares=[]
    for int in 1..number do 
      collect_squares << int**2
    end
    collect_squares.inject(:+)
  end
  
  def difference
    sum_of_squares > square_of_sums ? sum_of_squares - square_of_sums : square_of_sums - sum_of_squares
  end
  
  def square_of_sums
    collect_sums= 0
    for int in 1..number do 
      collect_sums += int
    end
    collect_sums**2
  end
  
end
