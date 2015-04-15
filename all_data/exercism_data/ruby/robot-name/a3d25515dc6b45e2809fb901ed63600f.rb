class Robot
  def name
    @name ||= NameGenerator.generate
  end

  def reset
    reset_name
  end

  private

  def reset_name
    @name = nil
  end
end


class NameGenerator
  def self.generate
    new.generate
  end

  def generate
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
