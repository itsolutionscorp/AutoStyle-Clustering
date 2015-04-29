class Robot
  LETTERS = ('a'..'z').to_a << ('A'..'Z').to_a
  NUMBERS = (0..9).to_a

  def name
    return @name if @name 
    @name = rand_letters(2) + rand_numbers(3)
  end

  def reset
    @name = nil
    name
  end

  def rand_letters(n)
    result = ""
    n.times do
      result << LETTERS[rand(LETTERS.length)].to_s
    end
    result
  end

  def rand_numbers(n)
    result = ""
    n.times do
      result << NUMBERS[rand(NUMBERS.length)].to_s
    end
    result
  end

end
