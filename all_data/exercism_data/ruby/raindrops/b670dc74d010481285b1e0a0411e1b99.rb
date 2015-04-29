class Raindrops
  def self.convert num
    rain_speak = {3 => "Pling", 5 => "Plang", 7 => "Plong"}

    working_str = ""

    rain_speak.each_pair do |key, value|
      if (num%key).zero?
        working_str += value
      end
    end

    working_str = num.to_s if working_str.empty?

    working_str

  end

end
