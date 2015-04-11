require 'securerandom'

class Robot
  def name
    @name ||= new_name
  end

  def reset
    @name = nil
  end

  def new_name
    random_2_alphabet = ('A'..'Z').to_a.shuffle[0,2].join
    random_3_number = format("%0#{3}d", SecureRandom.random_number(10**3))
    random_2_alphabet + random_3_number
  end
end
