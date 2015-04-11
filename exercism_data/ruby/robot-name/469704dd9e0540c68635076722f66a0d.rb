class Robot
  def name
    @name ||= Name.generate
  end

  def reset
    reset_name
  end

  private

  def reset_name
    @name = nil
  end
end


class Name
  def self.generate
    new.to_s
  end

  def to_s
    letters(2) + digits(3)
  end

  private

  def letters(count)
    ("A".."Z").to_a.sample(count).join
  end

  def digits(count)
    "%03d" % rand(10**count)
  end
end
