# Uses hash to write specific words if an input number is a multiple of
# 3,5, or 7.  Else, returns input number as string.

#Alternative form without functional line
#   PRIME_FACTORS.each do |number, word|
#     if i%number == 0  # if i contains the number as a prime factor, add corresponding word to string
#       response += word
#     end
#   end

class Raindrops
  PRIME_FACTORS = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong'}

  def self.convert(i)
    response = PRIME_FACTORS.map{|number,word| i%number == 0 ? word : ''}.join()
    response = i.to_s if response.empty?
    response
  end

end
