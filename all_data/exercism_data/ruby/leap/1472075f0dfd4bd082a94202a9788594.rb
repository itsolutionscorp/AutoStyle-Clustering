class Year
  def self.leap?(year)
    case
    when year % 400 == 0 then true
    when year % 100 == 0 then false
    when year % 4 == 0 then true
    else false
    end
  end
end
