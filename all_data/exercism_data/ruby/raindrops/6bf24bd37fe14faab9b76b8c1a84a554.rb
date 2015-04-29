module Raindrops
  def self.convert(num)
    output = ''
    [3,5,7].each{|x|
      if num%x==0
        case x
          when 3 then output<<'Pling'
          when 5 then output<<'Plang'
          when 7 then output<<'Plong'
        end
      end
    }
    return num.to_s if output.length==0
    output
  end
end
