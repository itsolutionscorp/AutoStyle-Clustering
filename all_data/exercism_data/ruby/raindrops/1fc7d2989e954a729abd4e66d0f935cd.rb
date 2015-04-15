class Raindrops
  def initialize(num)
    @num = num
  end

  def self.convert(num)
    @num = num
    array = []
    num_hash = {3 => "Pling", 5 => "Plang", 7 => "Plong"}
    num_hash.each do |key, value|
      if @num % key == 0
        array.push(value)
      end
    end
    if array == []
      p @num.to_s
    else
      p array.join
    end
  end
end

Raindrops.convert(3)



#     def convert
#     array = []
#     if @num%3 == 0
#       array.push("pling")
#     end
#     if @num%5 == 0
#       array.push("plang")
#     end
#     if @num%7 == 0
#       array.push("plong")
#     end
#     if @num%3 && num%5 && num%7 != 0
#       p num
#     end
#     p array.join
#   end
# end
