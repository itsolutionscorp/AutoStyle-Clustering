# Given number find prime factors and display message
# 3 - Pling, 5 - Plang, 7 - Plong

class Raindrops

  def self::convert(num)
    ret_str = ""
    ret_str << 'Pling' if(num % 3 == 0)
    ret_str << 'Plang' if(num % 5 == 0)
    ret_str << 'Plong' if(num % 7 == 0)
    ret_str << num.to_s if ret_str.empty?
    return ret_str
  end

end
