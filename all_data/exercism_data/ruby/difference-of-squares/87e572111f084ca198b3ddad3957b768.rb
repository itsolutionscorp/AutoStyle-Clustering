class Squares
  @@ints = 1.step.lazy
  @@squares = @@ints.map { |x| x*x }

  @@sum_of_squares = Enumerator.new { |yielder|
    yielder << 0
    s = @@squares.clone
    @@sum_of_squares.each do |n|
      yielder << n + s.next
    end
  }.lazy

  @@sums = Enumerator.new { |yielder|
    yielder << 0
    i = @@ints.clone
    @@sums.each do |n|
      yielder << n + i.next
    end
  }.lazy

  @@square_of_sums = @@sums.map { |x| x*x}
  
  def initialize(number)
    @number = number
  end

  def result_for(list)
    list.first(@number + 1).last
  end
    
  def sum_of_squares
    result_for(@@sum_of_squares)
  end

  def square_of_sums
    result_for(@@square_of_sums)
  end

  def difference
    square_of_sums - sum_of_squares
  end
end
