class Year
  def self.leap?(year)
    if year % 4 == 0
      if year % 100 == 0
        if year % 400 == 0
          true
        end
      else
        true
      end
    end
  end
end
