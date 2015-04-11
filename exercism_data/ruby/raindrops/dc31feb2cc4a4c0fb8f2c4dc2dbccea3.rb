class Raindrops
  @raindropHash = {
    Pling: 3,
    Plang: 5,
    Plong: 7,
  }
  
  def self.divisibleBy?(numberToTest, divisor)
    numberToTest % divisor == 0
  end

  def self.convert(numberToTest)
    raindropResult = ""

    @raindropHash.each do |sound, divisor|
      raindropResult << sound.to_s if divisibleBy?(numberToTest, divisor)
    end

    raindropResult.empty? ? numberToTest.to_s : raindropResult
  end
end
