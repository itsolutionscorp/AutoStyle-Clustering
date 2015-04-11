require 'pry'
require 'pry-nav'
require 'prime'

class Raindrops

  def self.convert(num)
    num_list = num.prime_division.flatten.select { |i| (num % i).zero? }
    msg = ""
    msg << "Pling" if num_list.include?(3) || num == 3
    msg << "Plang" if num_list.include?(5) || num == 5
    msg << "Plong" if num_list.include?(7) || num == 7

    if msg.empty?
      msg << num.to_s
    else
      msg
    end
  end
end
