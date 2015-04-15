class Year
  def self.leap?(date)
    if date%4 == 0
      if date%100 !=0
        return true
      elsif date%400 == 0
        return true
      end
    else
      return false
    end
  end
end
