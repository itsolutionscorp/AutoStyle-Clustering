class Raindrops
    @rain_speak = {3 => "Pling", 5 => "Plang", 7 => "Plong"}
    def self.convert(num)
        str = ""
        @rain_speak.each do |key, val|
            str += (num.to_i % key.to_i == 0) ? val : ""
        end
        return (str.eql? "") ? num.to_s : str
    end
end

puts Raindrops.convert(5)
