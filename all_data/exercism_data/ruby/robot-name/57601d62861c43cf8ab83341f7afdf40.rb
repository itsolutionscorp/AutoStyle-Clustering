class Robot
  def name
    @name ||= RobotNameGenerator.exercism_industries_standard
  end

  def reset
    reset_name
  end

  private

  def reset_name
    @name = nil
  end
end


class RobotNameGenerator
  def self.exercism_industries_standard
    new.generate(2, 3)
  end

  def generate(letter_count, digit_count)
    letters(letter_count) + digits(digit_count)
  end

  private

  def letters(count)
    ("A".."Z").to_a.sample(count).join
  end

  def digits(count)
    return "" if count.zero?
    "%0#{count}d" % rand(10**count)
  end
end
