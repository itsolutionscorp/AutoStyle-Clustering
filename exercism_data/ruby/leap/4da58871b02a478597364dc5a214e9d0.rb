class Year
  def self.leap?(y)
    if y % 4 == 0
      if y % 100 == 0
        if y % 400 == 0
          return true
        else
          return false
        end
      end
      return true
    end
  end
end
        
