class Robot
  def name
    name = ''
    2.times { name << ('a'..'z').to_a.sample.upcase }
    3.times { name << (0..9).to_a.sample.to_s }
    @name ||= name
  end

  def reset
    @name = nil
  end
end
