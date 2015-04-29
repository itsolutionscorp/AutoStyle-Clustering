class Year

  def self.leap?(year)
    div_by_4 = ((year % 4) == 0)
    div_by_100 = ((year % 100) == 0)
    div_by_400 = ((year % 400) == 0)

    if div_by_4
      if div_by_100
        if div_by_400
          return true
        else
          return false
      end
      else
        return true
      end
    else
      return false
    end
  end


end
