class Robot

  def name
    @robot_name ||= generate_new_name
  end

  def reset
    @robot_name = nil
  end

  private

  class << self; attr_reader :used_names end
  @used_names = []

  def generate_new_name
    @name_to_check = get_letters + get_number
    check_if_used_name
  end

  def check_if_used_name
    if Robot.used_names.any? {|used_name| used_name == @name_to_check}
      name
    else
      Robot.used_names << @name_to_check
      @name_to_check
    end
  end

  def get_letters
    (0..1).map {('A'..'Z').to_a[rand(26)]}.join
  end

  def get_number
    number = rand(0..999)
    sprintf("%03d", number).to_s
  end
end
