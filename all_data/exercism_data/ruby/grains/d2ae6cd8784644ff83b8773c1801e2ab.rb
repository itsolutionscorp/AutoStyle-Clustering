class Grains
  def initialize
    @solution_array = [1]
  end

  def square(sq_num)
    counter = 1
    (sq_num -1).times do  
      counter *= 2
      @solution_array << counter
    end
    counter
  end

  def total
    sum = 0
    square(64)
    @solution_array.each do |s|
      sum += s
    end
    sum
  end
end


#pass in x

#obj returned is double x

# passes chessboard square # to a new instance of Grains
#expect to get back previous square's number of grains * 2

# 
