module Year
  def self.leap?(year)
    (div_by_4, div_by_100, div_by_400) = [4, 100, 400].map {|d| year % d == 0 }
    div_by_4 && (!div_by_100 || div_by_400)
  end
end
