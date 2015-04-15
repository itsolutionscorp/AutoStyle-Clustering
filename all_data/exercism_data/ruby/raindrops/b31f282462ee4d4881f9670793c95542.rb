class Raindrops

  def self.factorial_test
    @plong = @number % 7 == 0
    @plang = @number % 5 == 0
    @pling = @number % 3 == 0
  end

  def self.convert(number)
    @number = number
    @raindrops = ""
    self.factorial_test

    if @pling == true then @raindrops += 'Pling' end
    if @plang == true then @raindrops += 'Plang' end
    if @plong == true then @raindrops += 'Plong' end
    if @plong == false && @plang == false && @pling == false then @raindrops += number.to_s end

    @raindrops
  end

end
