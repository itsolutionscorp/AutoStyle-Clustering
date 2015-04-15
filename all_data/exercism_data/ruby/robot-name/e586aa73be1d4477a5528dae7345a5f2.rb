class Robot
  def name
    @name ||= new_name
  end

  def reset
    @name = nil
  end

  private

  def new_name
    format '%c%c%03d', 'A'.ord + rand(26), 'A'.ord + rand(26), rand(1000)
  end
end
