class Raindrops

  def convert(number)

    s = ""
    ["pling", "plang", "plong"].each do |drop|
      message = drop + "?"
      if send message, number
        s << drop.capitalize
      end
    end

    if s == ""
      s << number.to_s
    end

    s

  end

  def pling?(number)
    number % 3 == 0
  end

  def plang?(number)
    number % 5 == 0
  end

  def plong?(number)
    number % 7 == 0
  end

end
