class Robot

  LETTERS = ('A'..'z').to_a.join

  def initialize
    @name = ''
  end

  def name
    if @name == ''
      @name << LETTERS[rand(LETTERS.length), 2]
      @name << rand(100..999).to_s
    else
      @name = @name
    end
  end

  def reset
    @name = ''
  end

end
