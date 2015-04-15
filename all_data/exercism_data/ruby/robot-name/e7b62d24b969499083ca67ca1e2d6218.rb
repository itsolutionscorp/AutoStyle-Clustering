class Robot
  def name
    @name ||= gen_name
  end
  def reset
    @name = nil
  end
  private
  def gen_name
    "#{2.times.map{('A'.ord+rand('A'.ord-'Z'.ord)).chr}.join}#{rand(100..999)}"
  end
end
