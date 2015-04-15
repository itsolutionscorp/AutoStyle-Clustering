class Robot
  def name
    return @name if @name
    abc = [*'A'..'Z']
    @name = abc.sample(2).join("") + rand(100).to_s.rjust(3, '0')
  end
  def reset
    @name = nil
  end
end
