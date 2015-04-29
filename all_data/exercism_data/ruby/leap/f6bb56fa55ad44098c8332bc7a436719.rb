class Year

  def self.leap?(year_to_check)
    if year_to_check % 4 == 0
      if year_to_check % 100 == 0
        return false unless year_to_check % 400 == 0
      end
      true
    else
      false
    end
  end

end
