class Raindrops

  def self.convert(number)
    @number = number
    @result = ""
    mod_all
    @result
  end

  def self.mod_all
    mod_3
    mod_5
    mod_7
    none_mod
  end

  def self.none_mod
    if @result == ""
      @result << @number.to_s
    end
  end

  def self.mod_3
    if @number % 3 == 0
      @result << "Pling"
    end
  end

  def self.mod_5
    if @number % 5 == 0
      @result << "Plang"
    end
  end

  def self.mod_7
    if @number % 7 == 0
      @result << "Plong"
    end
  end

end
