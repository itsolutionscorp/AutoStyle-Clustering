class Fixnum
  def method_missing(method_id, *args)
    if method_id.to_s.match(/^multiple_of_(\d+)\?$/)
      self % $1.to_i == 0 # is number modulo x?
    else
      super # pass the buck to superclass
    end
  end
end

class Year
  def self.leap?(year)
    year.multiple_of_4? and !year.multiple_of_100? or year.multiple_of_400?
  end
end
