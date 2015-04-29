class Robot

  def name
    @name = get_letters + get_numbers if !@name
  end

  def get_letters
    (0...2).map { (65 + rand(26)).chr }.join
  end

  def get_numbers
    (0...3).map { rand(9) }.join
  end

  def reset
    @name = nil
  end
end
