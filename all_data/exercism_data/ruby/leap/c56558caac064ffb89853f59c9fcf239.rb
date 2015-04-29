class Year
  def self.leap?(test) #Is there another way to do this without calling self?
    case
    when test % 400 == 0  
      true
    when test % 100 == 0
      false
    when test % 4 == 0
      true
    end
  end
end
