class Robot
  def name
    @name ||= two_letters + three_numbers
  end

  def reset
    @name = nil
  end

private

  def letter
    ('A'.ord + rand(26)).chr
  end

  def number
    rand(10).to_s
  end

  def three_numbers
    "#{number}#{number}#{number}"
  end

  def two_letters
    "#{letter}#{letter}"
  end
end
