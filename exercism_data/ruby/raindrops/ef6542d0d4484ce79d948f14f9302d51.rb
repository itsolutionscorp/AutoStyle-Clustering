module Raindrops

  SECRET = { 3=> "Pling", 5=> "Plang", 7=> "Plong" }

  def self.convert n
    words = ""
    SECRET.each { |div,word| words<<word if n%div==0 }
    words = n.to_s if words.length==0
    words
  end
end
