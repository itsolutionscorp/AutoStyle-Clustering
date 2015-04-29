class Year

  class << self
    def leap?(year)
      year % 4 == 0 ? ( year % 100 == 0 ? ( year % 400 == 0 ? true : false) : true ) : false
    end
  end

end
