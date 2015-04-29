class Robot
  attr_reader :name

  def initialize
    reset
  end

  def reset
    set_name_letters
    set_name_digits
  end

  private

  def set_name_letters
    @name = ""
    2.times { @name << ("A".."Z").to_a.shuffle.first }
  end

  def set_name_digits
    3.times { @name << rand(10).to_s }
    @name
  end
end
