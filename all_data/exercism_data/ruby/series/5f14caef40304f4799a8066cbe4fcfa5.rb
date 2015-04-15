class Series
  def initialize(numbers)
    @numbers = numbers.split('')
  end
  def slices(num)
    raise ArgumentError if num > @numbers.length
    result = [] 
    (0..@numbers.length - num).each {|n| 
      result[n] = []
      (0..num - 1).each {|x| result[n] << @numbers[n + x].to_i }
    }
    result
  end
end
