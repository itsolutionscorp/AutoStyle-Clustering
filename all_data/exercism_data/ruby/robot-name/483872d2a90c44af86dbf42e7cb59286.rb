class Robot
  def name
    @name ||= new_name
  end

  def reset
    @name = nil
  end

  private
  def new_name
    "%s%s" % [letters, numbers]
  end

  def letters
    (0...2).map { ("A".."Z").to_a.sample }.join
  end

  def numbers
    (0...3).map { (0..9).to_a.sample }.join
  end
end
