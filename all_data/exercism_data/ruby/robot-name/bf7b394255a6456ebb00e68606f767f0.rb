class Robot
  @@names = []

  def name
    @name ||= name_uniq
  end

  def reset
    @name = name_uniq
  end

  private

  def rand_name
    chars = ('A'..'Z').to_a.join + (0..9).to_a.join

    2.times.map { chars[rand(26)] }.join + 3.times.map { chars[26 + rand(10)] }.join
  end

  def name_uniq
    name = nil
    name = rand_name while @@names.include? name
    @@names << name

    name
  end
end
