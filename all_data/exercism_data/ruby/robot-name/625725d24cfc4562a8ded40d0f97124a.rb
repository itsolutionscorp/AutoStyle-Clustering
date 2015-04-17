class Robot

  def name
    @name ||= generate_name
  end

  def reset
    @name = generate_name
  end

  private 
  def generate_name
    @all_bot_names ||= []
    new_name = ''

    loop do
      ascii_chars = ('A'..'Z').to_a
      letters = (0..1).map { ascii_chars.sample }.join 
      numbers = (0..2).map {rand(9)}.join
      new_name = letters + numbers 
      break unless new_name == @name || @all_bot_names.include?(new_name)
    end

    @all_bot_names << new_name
    new_name

  end
  
end