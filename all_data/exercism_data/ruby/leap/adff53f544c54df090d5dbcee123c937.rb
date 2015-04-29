class Year
  def self.leap?(year)
    if year % 4 == 0
      if (year % 100 == 0) && (year % 400 != 0)
        false
      else
        true
      end
    else
      false
    end
  end
end

=begin
if (@year %  400 == 0) && (@year % 100 == 0)
  true
else
  false
end
=end
