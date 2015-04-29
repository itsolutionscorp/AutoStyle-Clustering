class Year
  def self.leap?(number)
    if number % 4 == 0
      if number % 100 == 0
        if number % 400 == 0
          true
        else
          false
        end
      else
        true
      end
    else
      false
    end
  end
end
