class Raindrops

  def self.convert(value)
    response = []

    if pling?(value) 
      response << "Pling"
    end

    if plang?(value)
      response << "Plang"
    end

    if plong?(value) 
      response << "Plong"
    end

    if !pling?(value) && !plang?(value) && !plong?(value)
      response << "#{value}"
    end

    response.join
  end

  private

  def self.pling?(num)
    num % 3 == 0
  end

  def self.plang?(num)
    num % 5 == 0
  end

  def self.plong?(num)
    num % 7 == 0
  end
end
