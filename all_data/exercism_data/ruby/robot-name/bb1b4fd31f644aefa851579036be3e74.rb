class Robot
  def name
    @_name ||= (random_alphabet +
                random_alphabet +
                random_number +
                random_number +
                random_number)
  end

  def reset
    @_name = nil
  end

  private

  def random_alphabet
    ('A'..'Z').to_a[rand(0..25)]
  end

  def random_number
    rand(0..9).to_s
  end
end
