class Raindrops
  def self.convert(number)
    if number % 3 == 0 || number % 5 == 0 || number % 7 == 0
      "#{ if number % 3 == 0 then "Pling" else "" end }" +
      "#{ if number % 5 == 0 then "Plang" else "" end }" +
      "#{ if number % 7 == 0 then "Plong" else "" end }"
    else
      number.to_s
    end
  end
end
