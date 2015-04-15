class Year
  def initialize(number)
    @number = number
  end

  def leap?  
    case
    when @number % 100 == 0
      @number % 400 == 0
    else
      @number % 4 == 0
    end
  end

end
