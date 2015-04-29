class Year
  def self.leap?(year)
    if year.div_by?(100)
      year.div_by?(400) ? true : false
    else
      year.div_by?(4) ? true : false
    end

    # it can be just one line of code but I don't like it.
    # year.div_by?(400) ? true : year.div_by?(100) ? false : year.div_by?(4) ? :true : false
  end 
end

class Fixnum
  def div_by?(div)
    self % div == 0 ? true : false
  end
end
