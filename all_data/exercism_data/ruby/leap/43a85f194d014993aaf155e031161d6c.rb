class Year
  def self.leap?(year)
    case year
    when lambda { |y| y % 400 == 0 }
      true
    when lambda { |y| y % 100 == 0 }
      false
    when lambda { |y| y % 4 == 0 }
      true
    end
  end
end
