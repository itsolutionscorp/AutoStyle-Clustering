class Raindrops

  def self.prime_condition
    @plong = @number % 7 == 0
    @plang = @number % 5 == 0
    @pling = @number % 3 == 0
  end

  def self.convert(number)
    @number = number
    @raindrops = ""
    self.prime_condition

    @raindrops += 'Pling' if @pling
    @raindrops += 'Plang' if @plang
    @raindrops += 'Plong' if @plong
    @raindrops += number.to_s if (!@plong && !@plang && !@pling)


    @raindrops
  end

end
