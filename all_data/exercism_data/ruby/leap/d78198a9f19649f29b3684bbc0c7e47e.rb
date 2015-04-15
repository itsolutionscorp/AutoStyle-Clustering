class Year
  def initialize(number)
    @number = number
  end

  def leap?  
    case
    when @number % 100 == 0
      if @number % 400 == 0
        true
      else
        false
      end
    when @number % 4 == 0
      true
    else
      false
    end
  end

end
