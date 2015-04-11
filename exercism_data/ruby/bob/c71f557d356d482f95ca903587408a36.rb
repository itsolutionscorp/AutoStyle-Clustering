class Bob  
  def hey(input)
    case input
    when is_empty?
      "Fine. Be that way."
    when upcased?
      "Woah, chill out!"
    when ends_with_?
      "Sure."
    else
      "Whatever."
    end
  end
  
  private
    
  def is_empty?
    Proc.new{|string| string == nil || string.empty?}
  end
  
  def upcased?
    Proc.new{|string| string == string.upcase }
  end
  
  def ends_with_?
    Proc.new{|string| string.end_with?("?")}
  end
end
