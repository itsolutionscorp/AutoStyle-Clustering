class Raindrops
  @possible = {"Pling" => 3, "Plang" => 5, "Plong" => 7}

  def self.convert(number)
    result = ""

    @possible.each do |drop, value|
      result << drop if number % value == 0
    end

    result.empty? ? number.to_s : result
  end
end
