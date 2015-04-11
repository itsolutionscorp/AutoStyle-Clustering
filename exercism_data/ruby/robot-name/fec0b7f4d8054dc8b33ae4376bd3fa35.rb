class Robot

  def name
    @robot_name ||= generate_new_name
  end

  def reset
    @robot_name = nil
  end

  private

  def check_if_used_name
    @used_names ||= []
    name if @used_names.any? {|used_name| used_name == @robot_name}
    @used_names << @robot_name
    @robot_name
  end

  def generate_new_name
    @robot_name = get_letters + get_number
    check_if_used_name
  end

  def get_letters
    (0..1).map {('A'..'Z').to_a[rand(26)]}.join
  end

  def get_number
    number = rand(0..999)
    sprintf("%03d", number).to_s
  end
end
