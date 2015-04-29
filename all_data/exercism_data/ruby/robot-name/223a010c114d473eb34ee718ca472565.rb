class Robot
  def name
    @name ||= NameFactory.name
  end

  def reset
    reset_name
  end

  private

  def reset_name
    @name = nil
  end
end


class NameFactory
  def self.name
    new.name
  end

  def name
    [letter, letter, digit, digit, digit].join
  end

  private

  def letter
    ("A".."Z").to_a.sample
  end

  def digit
    rand(10)
  end
end
