class Complement
  def self.of_dna(d)
    d.chars.map{|c| case c
                      when "G" then "C"
                      when "C" then "G"
                      when "T" then "A"
                      when "A" then "U"
                    end}.join("")
  end
end
