class Year
  def self.leap?(i)
    if (i % 4) == 0
      if (i % 100) == 0
        return false
      end unless (i % 400) == 0
      return true
    end
  end
end
