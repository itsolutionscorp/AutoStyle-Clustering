class Year

  def self.leap?(year)

    case 
    when year.to_i % 100 == 0 && year % 400 != 0
      return false
    when year.to_i % 4 == 0
      return true
    else
      return false
    end
  end
end
