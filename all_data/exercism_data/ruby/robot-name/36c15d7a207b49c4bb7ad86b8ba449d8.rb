class Robot
  def name
    unless @name
      @name = ''
      letters = ('A'..'Z').to_a + ('a'..'z').to_a
      numbers = ('0'..'9').to_a
      2.times { @name << letters.sample }
      3.times { @name << numbers.sample }
    end

    @name
  end

  def reset
    @name = nil
  end
end
