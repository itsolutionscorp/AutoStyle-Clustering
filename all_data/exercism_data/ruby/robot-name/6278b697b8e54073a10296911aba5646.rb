class Robot
  def name
    @name ||= sprintf("%2s%03d", prefix, suffix)
  end

  def reset
    @name = nil
  end

  private

  def prefix
    2.times.map { (65 + rand(26)).chr }.join
  end

  def suffix
    rand(999)
  end
end
