class Robot
  attr_reader :rl
  def initialize
    @rl = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
  end

  def name
    named = ""
    named << rl[rand(25).to_i]
    named << rl[rand(25).to_i]
    named << rand(100..999).to_s
    save_name(named)
    check_name
    named
  end
  
  def reset
    @stored_names = Array.new
  end
  
  private
  
  def save_name(named)
    stored_names << named
  end
  
  def check_name
    stored_names.uniq!
  end
  
  def stored_names
    @stored_names ||= []
  end
  
end
