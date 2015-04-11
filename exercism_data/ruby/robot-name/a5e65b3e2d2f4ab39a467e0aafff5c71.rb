class Robot
  def name
    @name || reset
  end

  def reset
    @name = (("A".."Z").to_a.sample(2) +
      ("1".."9").to_a.sample(3)).join
  end
end
