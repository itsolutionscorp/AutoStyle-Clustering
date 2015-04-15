class Raindrops

  def self.convert(value)

    response = ""

    response << "Pling" if value % 3 == 0
    response << "Plang" if value % 5 == 0
    response << "Plong" if value % 7 == 0

    response.length == 0 ? value.to_s : response

  end

end
