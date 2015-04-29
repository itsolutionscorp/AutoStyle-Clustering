class Bob

  def hey(remark)
    remark = Remark.new(remark)
    case 
    when remark.silent?       then "Fine. Be that way!"
    when remark.shouting?     then "Woah, chill out!"
    when remark.questioning?  then "Sure."
    else                           "Whatever."
    end
  end

end

class Remark
  attr_reader :remark

  def initialize(remark)
    @remark = remark
  end

  def silent?
    remark.strip.empty?
  end

  def shouting?
    has_letters = remark.match(/[A-Z]/)
    all_upper_case = (remark == remark.upcase)
    has_letters && all_upper_case
  end

  def questioning?
    remark.end_with?("?")
  end
end
