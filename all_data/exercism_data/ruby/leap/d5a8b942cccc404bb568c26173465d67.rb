class Year

  def self.leap?(year)
    if year % 400 == 0 then true
      elsif year % 100 ==0 then false
      elsif year % 4 == 0 then true
      else false
    end

  end

end
