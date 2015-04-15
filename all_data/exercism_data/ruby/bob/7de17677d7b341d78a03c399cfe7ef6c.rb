class SayWhat
  def initialize(what)
    @what = what
  end
  
  def shouting?
    @what == @what.upcase
  end
  
  def question?
    @what =~ /\?$/
  end
  
  def blank?
    @what.each_char { |character| return false if character =~ /\w/ }
    true
  end  
  
  def not_all_numbers?
    @what.each_char { |character| return true if character =~ /[a-zA-Z]/ }
    false
  end
  
  def single_line?
    @what.count("\n").zero? 
  end
end

class Bob
  def hey(what)
    dialoge = SayWhat.new(what)  
    if dialoge.shouting? && dialoge.not_all_numbers?
      "Woah, chill out!"
    elsif dialoge.question? && dialoge.single_line?
      "Sure."
    elsif dialoge.blank?
      "Fine. Be that way!"        
    else
      "Whatever."
    end
  end
end
