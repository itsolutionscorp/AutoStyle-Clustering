class Robot

  def name
    @name ||= pick('A'..'Z') + pick('A'..'Z') + pick('0'..'9') + pick('0'..'9') + pick('0'..'9')
  end
  
  def reset
    @name = nil
  end
  
private
  
  def pick(range)
    a = range.to_a
    a[rand(a.size)]
  end

end
