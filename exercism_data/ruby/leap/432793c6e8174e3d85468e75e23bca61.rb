class Year
  attr_accessor :year_num

  def initialize(number)
    self.year_num = number
  end

  def leap?
    if year_num % 400 == 0
      true
    elsif year_num % 4 == 0
      if year_num % 100 == 0
        false
      else
        true
      end
    end
  end
end
