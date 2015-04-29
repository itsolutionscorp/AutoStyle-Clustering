class Robot
  attr_reader :name

  def initialize
    reset
  end

  def reset
    @name = set_name_letters + set_name_digits
  end

  private

  def set_name_letters
    ("A".."Z").to_a.shuffle[0..1].join
  end

  def set_name_digits
    rand(1000).to_s.rjust(3, "0")
  end
end
