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
    output==''? num.to_s : output
  end
end
