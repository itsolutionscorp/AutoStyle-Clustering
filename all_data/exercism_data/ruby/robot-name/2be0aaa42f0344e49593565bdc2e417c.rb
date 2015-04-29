class Robot
  LETTERS = ('A'..'Z').to_a

  attr_reader :name

  def name
    @name ||= name_generator
  end

  def name_generator
    temp_name = ''
    2.times { temp_name += LETTERS.sample}
    temp_name += rand(999).to_s.ljust(3, '0')
  end

  def reset
    @name = nil
  end
end
