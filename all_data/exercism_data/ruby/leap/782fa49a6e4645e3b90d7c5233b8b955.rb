class Year

  def self.leap?(num)
    if num % 4 == 0
      if (num % 400 != 0) && (num % 100 == 0)
        return false
      else
        return true
      end
    end

  end
end
