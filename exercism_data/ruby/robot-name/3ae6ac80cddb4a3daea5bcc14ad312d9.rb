class Robot

  AVAILABLE_LETTERS = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ'

  def initialize
    @name = ""
  end

  def name
    if @name == ""
      @name = (AVAILABLE_LETTERS[rand(52), 2] + rand(100..999).to_s)
    else
      @name = @name
    end
  end

  def reset
    @name = ""
  end

end
