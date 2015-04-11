class Robot
  def name
    @name ||= rand_name
  end

  def reset
    @name = rand_name
  end

  private
  
  def rand_name
    chars = ('A'..'Z').to_a.join + (0..9).to_a.join

    2.times.map { chars[rand(26)] }.join + 3.times.map { chars[26 + rand(10)] }.join
  end
end
