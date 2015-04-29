PRIMERS = { Pling: 3, Plang: 5, Plong: 7 }

class Raindrops
  def self.convert(num)
    response = PRIMERS.inject("") do |result, ( resp, fact)|
        num % fact == 0 ? result += resp.to_s : result
    end
    response.empty? ? num.to_s : response
  end
end
