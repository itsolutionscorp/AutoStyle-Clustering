class Raindrops
  def self.convert(number)
    rain = []                                       # => []
    [3,5,7].each do |prime|                         # => [3, 5, 7]
      if number % (prime) == 0 then                 # => true, true, true
        case prime                                  # => 3, 5, 7
        when 3 then rain.push("Pling")              # => ["Pling"]
        when 5 then rain.push("Plang")              # => ["Pling", "Plang"]
        when 7 then rain.push("Plong")              # => ["Pling", "Plang", "Plong"]
        end                                         # => ["Pling"], ["Pling", "Plang"], ["Pling", "Plang", "Plong"]
      end                                           # => ["Pling"], ["Pling", "Plang"], ["Pling", "Plang", "Plong"]
    end                                             # => [3, 5, 7]
    if rain.empty? then rain.push(number.to_s) end  # => nil
    rain.join                                       # => "PlingPlangPlong"
  end

end
