class Robot
  def name
    @name ||= letter_prefix + number_suffix
  end

  def letter_prefix
    ("A".."Z").to_a.sample(2).join
  end

  def number_suffix
    ("0".."9").to_a.sample(3).join
  end

  def reset
    @name = nil
  end
end
