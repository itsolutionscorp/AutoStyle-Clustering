class Robot

  def name
    @name ||= pick_name
  end
  
  def reset
    @name = nil
  end
  
private

  def pick_name
    pick('A'..'Z') + pick('A'..'Z') + pick('0'..'9') + pick('0'..'9') + pick('0'..'9')
  end
  
  def pick(range)
    a = range.to_a
    a[rand(a.size)]
  end

end
