class Raindrops

  def self.convert(number)

    @number = number

    result = ""

    drop3 = self.set_drops(3, "Pling")
    drop5 = self.set_drops(5, "Plang")
    drop7 = self.set_drops(7, "Plong")

    if drop3 != "" || drop5 != "" || drop7 != ""
      result = drop3 + drop5 + drop7
    else
      result = @number.to_s
    end

  end

  def self.set_drops(prime, drop)

    if @number % prime == 0
      drop
    else
      ""
    end

  end

end
