class Year
  def self.leap?(year)
    case
    when year % 400 == 0 then true
    when year % 100 == 0 then false
    else year % 4 == 0
    end
  end
end
