class Raindrops
  def self.convert(n)
    response = ""
    if n % 3 == 0
      response << "Pling"
    end
    if n % 5 == 0
      response << "Plang"
    end
    if n % 7 == 0
      response << "Plong"
    end

    response.empty? ? n.to_s : response
  end
end
